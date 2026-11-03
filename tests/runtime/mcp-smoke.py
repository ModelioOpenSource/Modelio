"""Read-only MCP checks against a project populated by jdk27-smoke.py."""
import argparse
import json
import urllib.request


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--url', default='http://127.0.0.1:7341/mcp')
    parser.add_argument('--project', required=True)
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

    def tool(name, arguments):
        reply = rpc('tools/call', {'name': name, 'arguments': arguments})
        assert not reply.get('isError'), reply
        return json.loads(reply['content'][0]['text'])

    rpc('initialize', {'protocolVersion': '2025-03-26', 'capabilities': {},
                       'clientInfo': {'name': 'jdk27-smoke', 'version': '1'}})
    names = {tool['name'] for tool in rpc('tools/list')['tools']}
    assert {'find_elements', 'get_element', 'get_diagram_contents'} <= names
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
    tool('get_diagram_contents', {'diagram_id': diagrams['elements'][0]['id']})
    print('MCP_SMOKE_OK: persisted project, exact/substring search, element and diagram reads')


if __name__ == '__main__':
    main()
