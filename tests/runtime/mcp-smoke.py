"""MCP checks against a disposable project populated by jdk27-smoke.py."""
import argparse
import base64
import json
import struct
import urllib.request


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--url', default='http://127.0.0.1:7341/mcp')
    parser.add_argument('--project', required=True)
    parser.add_argument('--write', action='store_true',
                        help='also test UML associations and diagram cleanup in the test project')
    args = parser.parse_args()

    def rpc(method, params=None):
        body = json.dumps({'jsonrpc': '2.0', 'id': 1, 'method': method,
                           'params': params or {}}).encode()
        request = urllib.request.Request(args.url, data=body,
                                         headers={'Content-Type': 'application/json'})
        with urllib.request.urlopen(request, timeout=30) as response:
            reply = json.load(response)
        assert 'error' not in reply, reply
        return reply['result']

    def call(name, arguments):
        reply = rpc('tools/call', {'name': name, 'arguments': arguments})
        assert not reply.get('isError'), reply
        return reply['content'][0]

    def tool(name, arguments):
        return json.loads(call(name, arguments)['text'])

    def diagram_image(diagram_id):
        image = call('get_diagram_image', {'diagram_id': diagram_id, 'max_size': 256})
        assert image['type'] == 'image' and image['mimeType'] == 'image/png'
        png = base64.b64decode(image['data'], validate=True)
        assert png[:8] == b'\x89PNG\r\n\x1a\n' and png[12:16] == b'IHDR'
        width, height = struct.unpack('>II', png[16:24])
        assert 0 < width <= 256 and 0 < height <= 256, (width, height)
        return png

    rpc('initialize', {'protocolVersion': '2025-03-26', 'capabilities': {},
                       'clientInfo': {'name': 'jdk27-smoke', 'version': '1'}})
    names = {tool['name'] for tool in rpc('tools/list')['tools']}
    assert {'find_elements', 'get_element', 'get_diagram_contents', 'get_diagram_image'} <= names
    project = tool('get_project_info', {})
    assert project['name'] == args.project, project
    assert project['dirty'] is False, project

    # Regression: exact lookup without a metaclass must work for named subclasses
    # of Element, even though Element itself has no Name attribute.
    found = tool('find_elements', {'name': 'Jdk27SmokeClass', 'exact': True})
    assert found['total_matches'] == 1, found
    class_id = found['elements'][0]['id']
    typed = tool('find_elements', {'name': 'Jdk27SmokeClass', 'exact': True,
                                    'metaclass': 'Standard.Class'})
    assert typed['elements'][0]['id'] == class_id, typed
    wrong_case = tool('find_elements', {'name': 'jdk27smokeclass', 'exact': True})
    assert wrong_case['total_matches'] == 0, wrong_case
    substring = tool('find_elements', {'name': 'jdk27smokeclass'})
    assert substring['elements'][0]['id'] == class_id, substring
    element = tool('get_element', {'id': class_id})
    assert element['name'] == 'Jdk27SmokeClass', element

    diagrams = tool('find_elements', {'name': 'Jdk27SmokeDiagram', 'exact': True})
    assert diagrams['total_matches'] == 1, diagrams
    diagram_id = diagrams['elements'][0]['id']
    tool('get_diagram_contents', {'diagram_id': diagram_id})
    before = diagram_image(diagram_id)
    assert tool('get_project_info', {})['dirty'] is False
    if args.write:
        check_diagram_associations(tool, class_id, diagram_id)
        assert diagram_image(diagram_id) != before
    print('MCP_DIAGRAM_IMAGE_OK: PNG preview, size limit and read-only access')
    print('MCP_SMOKE_OK: persisted project, exact/substring search, element and diagram reads')


def check_diagram_associations(tool, class_id, class_diagram_id):
    package = tool('find_elements', {'name': 'Jdk27SmokePackage', 'exact': True})
    owner_id = package['elements'][0]['id']

    def create(metaclass, **fields):
        return tool('create_element', {'metaclass': 'Standard.' + metaclass,
                                       **fields})['element']['id']

    diagram_id = create('UseCaseDiagram', owner_id=owner_id, name='McpUseCaseDiagram')
    actor_id = create('Actor', owner_id=owner_id, name='Cumplimiento / Auditor')
    use_case_id = create('UseCase', owner_id=owner_id, name='McpUseCase')
    association_id = create('Association', source_id=actor_id, target_id=use_case_id)
    added = tool('add_to_diagram', {'diagram_id': diagram_id,
                                    'element_ids': [association_id, actor_id, use_case_id],
                                    'with_relationships': False})
    assert added['added_count'] == 3, added
    relationships = tool('get_relationships', {'id': actor_id})['relationships']
    role_id = next(rel['id'] for rel in relationships
                   if rel['metaclass'] == 'AssociationEnd'
                   and any(end['id'] == use_case_id for end in rel['targets']))
    again = tool('add_to_diagram', {'diagram_id': diagram_id, 'element_ids': [role_id]})
    assert again['added_count'] == 0, again

    second_case_id = create('UseCase', owner_id=owner_id, name='McpSecondUseCase')
    create('AssociationEnd', source_id=second_case_id, target_id=actor_id)
    automatic = tool('add_to_diagram', {'diagram_id': diagram_id, 'element_ids': [second_case_id]})
    assert automatic['relationships_added_count'] == 1, automatic
    contents = tool('get_diagram_contents', {'diagram_id': diagram_id})
    assert len(contents['links']) == 2, contents
    assert not any(node.get('missing') for node in contents['nodes']), contents
    cleanup = tool('remove_from_diagram', {'diagram_id': diagram_id, 'missing': True})
    assert cleanup['removed_count'] == 0, cleanup
    assert tool('get_diagram_contents', {'diagram_id': diagram_id}) == contents
    assert {frozenset((link['from_id'], link['to_id'])) for link in contents['links']} == {
        frozenset((actor_id, use_case_id)), frozenset((actor_id, second_case_id))}
    resized = tool('move_in_diagram', {'diagram_id': diagram_id,
                                      'moves': [{'element_id': actor_id, 'label_width': 220}]})
    assert resized['nodes'][0]['label_width'] == 220, resized
    print('MCP_USE_CASE_ASSOCIATIONS_OK: explicit and automatic drawing, actor label resized independently')

    target_id = create('Class', owner_id=owner_id, name='McpOrderLine')
    class_association_id = create('Association', source_id=class_id, target_id=target_id,
                                  name='contains', source_multiplicity='1', target_multiplicity='1..*')
    association = tool('get_element', {'id': class_association_id})
    assert association['name'] == 'contains', association
    ends = association['references']['End']['elements']
    assert len(ends) == 2, ends
    ends_by_participant = {}
    for end in ends:
        details = tool('get_element', {'id': end['id']})
        attributes = details['attributes']
        ends_by_participant[details['participant_id']] = (attributes['MultiplicityMin'], attributes['MultiplicityMax'])
    assert ends_by_participant == {class_id: ('1', '1'), target_id: ('1', '*')}, ends_by_participant
    tool('add_to_diagram', {'diagram_id': class_diagram_id,
                            'element_ids': [class_id, target_id, class_association_id]})
    links = tool('get_diagram_contents', {'diagram_id': class_diagram_id})['links']
    link = next(link for link in links if link.get('association_id') == class_association_id)
    assert {link['from_id'], link['to_id']} == {class_id, target_id}, link
    moved = tool('move_in_diagram', {'diagram_id': class_diagram_id, 'reset_routes': True,
                                    'moves': [{'element_id': class_id, 'x': 40, 'y': 50},
                                              {'element_id': target_id, 'x': 300, 'y': 200}]})
    assert moved['routes_reset_count'] == 1, moved
    aligned = tool('align_in_diagram', {'diagram_id': class_diagram_id, 'reset_routes': True,
                                        'element_ids': [class_id, target_id], 'align': 'middle'})
    assert aligned['routes_reset_count'] == 1, aligned
    print('MCP_CLASS_ASSOCIATIONS_OK: named association, multiplicities and route reset after layout')

    part_id = create('Class', owner_id=owner_id, name='McpPart')
    composition_end_id = create('AssociationEnd', source_id=class_id, target_id=part_id,
                                composition=True, target_multiplicity='0..*')
    end = tool('get_element', {'id': composition_end_id})
    assert end['references']['Source']['elements'][0]['id'] == class_id, end
    assert end['participant_id'] == part_id, end
    assert end['attributes']['Aggregation'] == 'KINDISCOMPOSITION', end
    other_id = end['references']['Opposite']['elements'][0]['id']
    whole = tool('get_element', {'id': other_id})
    assert whole['participant_id'] == class_id, whole
    assert (whole['attributes']['MultiplicityMin'], whole['attributes']['MultiplicityMax']) == ('1', '1'), whole
    drawn = tool('add_to_diagram', {'diagram_id': class_diagram_id,
                                    'element_ids': [part_id, composition_end_id]})
    assert drawn['added_count'] == 2, drawn
    print('MCP_COMPOSITION_OK: source is the whole and target is the part')

    for removal_id in (class_association_id, link['id']):
        removed = tool('remove_from_diagram', {'diagram_id': class_diagram_id,
                                               'element_ids': [removal_id]})
        assert removed['removed_count'] == 1, removed
        remaining = tool('get_diagram_contents', {'diagram_id': class_diagram_id})['links']
        assert all(item.get('association_id') != class_association_id for item in remaining), remaining
        assert tool('get_element', {'id': class_association_id})['name'] == 'contains'
        redrawn = tool('add_to_diagram', {'diagram_id': class_diagram_id, 'element_ids': [class_association_id]})
        assert redrawn['added_count'] == 1, redrawn

    deleted = tool('delete_elements', {'ids': [class_association_id, link['id']]})
    assert deleted['deleted_count'] == 1 and deleted['deleted'][0]['id'] == class_association_id, deleted
    deleted_role = tool('delete_elements', {'ids': [composition_end_id]})
    assert deleted_role['deleted_count'] == 1, deleted_role
    final = tool('get_diagram_contents', {'diagram_id': class_diagram_id})
    assert final['links'] == [], final
    assert {class_id, target_id, part_id} <= {node.get('id') for node in final['nodes']}, final
    for participant_id in (class_id, target_id, part_id):
        relationships = tool('get_relationships', {'id': participant_id})['relationships']
        assert not any(item['metaclass'] == 'AssociationEnd' for item in relationships), relationships
    print('MCP_ASSOCIATION_REMOVAL_OK: hide or delete by association or end UUID')


if __name__ == '__main__':
    main()
