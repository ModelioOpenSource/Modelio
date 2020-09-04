/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.xmi.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.uml2.uml.Property;

@objid ("38d2e701-6627-449e-ac90-cfc0cacd4c2e")
public class ObjingEAnnotation extends XMIEAnnotation {
    @objid ("ab269565-a5f9-488b-93b4-76c15407e3a5")
    public static final String ACCESS_MODE = "AccessMode";

    @objid ("2bf79b9b-cca1-403b-b64b-1c083d0b77f1")
    public static final String ACCESS_NONE_VALUE = "no_access";

    @objid ("538abe89-ea0b-47ad-93d5-2039c646f439")
    public static final String BASECLASS = "BaseClass";

    @objid ("f17efce1-d406-4240-8cfc-31d3cdbd2c22")
    public static final String DIAGRAMNAME = "DiagramName";

    @objid ("34931918-6ff8-45f5-8771-2fa2460d7efb")
    public static final String DISCRIMINATOR = "Discriminator";

    @objid ("77610b13-68e1-49de-b96c-7250fab19018")
    public static final String ECOREID = "EcoreId";

    @objid ("1b80d6dc-b546-445f-a572-c1eb04f2e243")
    public static final String EFFECT = "Effect";

    @objid ("bb317dfa-2646-4f55-a3f7-302d3955ad8b")
    public static final String ELT_ID = "ID";

    @objid ("eb93e663-47de-4b68-9093-c5f696fc60ee")
    public static final String ELT_IMPORT_PATH = "ElementImportPath";

    @objid ("c9e5ab93-05bc-42f6-88ac-32150775d3b6")
    public static final String ELT_NAME = "Name";

    @objid ("9581817a-baa7-4a46-8ea7-abdd47a5a9fb")
    private static final String ENDLINENUMBER = "EndLineNumber";

    @objid ("3b41b954-1a6f-4f6b-a497-00ddbbcc4033")
    public static final String EXPORTERVERSION = "exporterVersion";

    @objid ("abf23407-517b-4215-bf7e-0dabeb23ceb6")
    public static final String ICONTYPE = "IconType";

    @objid ("0deb8905-99dd-408f-8080-ac0777ce61a5")
    public static final String IS_ABSTRACT = "isIsAbstract";

    @objid ("a23ba628-e548-4bb5-a24a-ca7d6aba03b6")
    public static final String IS_ATTRIBUTELINK = "isAttributeLink";

    @objid ("96e235c9-2a4f-4f80-99f0-206794c81c18")
    public static final String IS_BEHAVIOR = "isBehavior";

    @objid ("d8fbbaca-b3f7-4257-8574-57d28e621137")
    public static final String IS_BINDABLEINSTANCE = "isBindableInstance";

    @objid ("2ae6d582-5750-4969-8914-9dd93a9eb972")
    public static final String IS_COMBINEDUPLICATE = "isCombineDuplicate";

    @objid ("c93a599f-7531-412b-b40f-04ab30162227")
    public static final String IS_CONCURRENT = "isConcurrent";

    @objid ("07c85e04-67df-4cfb-a4f3-74eda6030d47")
    public static final String IS_CONNECTOR = "isConnector";

    @objid ("92513584-6204-40ce-8627-8f361fccb3f9")
    public static final String IS_CONSTANT = "isConstant";

    @objid ("19559c7f-3817-4f37-85c2-b573cf6dfe87")
    public static final String IS_DELETED = "isDeleted";

    @objid ("3c91aedb-977f-4fc6-a134-a495f526809a")
    public static final String IS_ELEMENTARY = "isPrimitive";

    @objid ("22d5dd48-8eed-4ce6-a6d8-7973f8375933")
    public static final String IS_EVENT = "isEvent";

    @objid ("e72e5ebb-6bfe-4e6f-82d9-e9981acceddd")
    public static final String IS_EXCEPTION = "isException";

    @objid ("53497819-9abd-4ae4-a5f8-5b0d6011a841")
    public static final String IS_EXPANSION = "isExpansion";

    @objid ("92f7c73e-49f1-4190-9e44-58651b34ed82")
    public static final String IS_HIDDEN = "isHidden";

    @objid ("86c982f3-1f32-4de0-a76f-db8e5de1534c")
    public static final String IS_INSTANTIABLE = "isInstantiable";

    @objid ("1d3ee6f1-4af2-4cfc-9eeb-ece5a7ae72f6")
    public static final String IS_LEAF = "isLeaf";

    @objid ("79b8140a-2a9b-4dbf-89b6-ab1c3eafe08d")
    public static final String IS_LINK = "isLink";

    @objid ("8e041dd3-b304-436e-a03b-3bed6dfc7a4d")
    public static final String IS_MAIN = "isMain";

    @objid ("07a37095-6df4-48b9-b2cc-51100ebee068")
    public static final String IS_NAMED_WITH_CONVENTION = "isNamedWithConvention";

    @objid ("cae48919-79c0-495b-a6b0-d0c24bf712f1")
    public static final String IS_NARYLINK = "isNaryLink";

    @objid ("58b579a0-47f4-4176-9f2a-0229a1fcdde6")
    public static final String IS_NAVIGABLE = "isNavigable";

    @objid ("20d74283-143b-4e5b-93d6-ad2d5e29fb7e")
    public static final String IS_NOTETYPE = "isNoteType";

    @objid ("04ee228f-26b0-426d-9439-f6918a548124")
    public static final String IS_NOTYPE = "isNoType";

    @objid ("1d6eccf6-1b2a-4959-9c05-c8e31ef057c0")
    public static final String IS_ORDERED = "isIsOrdered";

    @objid ("7d689d91-e646-4ed6-99ee-aa6d03388d2f")
    public static final String IS_OWNED_BY_ASSOCIATION = "isOwnedByAssociation";

    @objid ("54d2a9ec-c282-427f-b4a2-efa6e8c1d722")
    public static final String IS_OWNED_BY_ASSOCIATION_CLASS = "isOwnedByAssociationClass";

    @objid ("0d36e1de-bf71-4266-9c56-ef81baa75624")
    public static final String IS_PORT = "isPort";

    @objid ("1b424def-8a3e-45cf-8207-104304aa943b")
    public static final String IS_PROVIDED_INTERFACE = "isProvidedInterface";

    @objid ("bec19dd0-e54f-4b12-a769-16c4f0bea603")
    public static final String IS_REENTRANT = "isReentrant";

    @objid ("7d8972bb-b67f-42ea-abcd-23cc3c409884")
    public static final String IS_REFERENCE = "isReference";

    @objid ("36943562-36ef-43f6-bc28-e6ff67409190")
    public static final String IS_REQUIRED_INTERFACE = "isRequiredInterface";

    @objid ("b045215d-3dfd-4a5d-95f2-c2a5fa967ea4")
    public static final String IS_REQUIREMENT_CONTAINER = "isRequirementContainer";

    @objid ("c69a2ed4-c84c-40ab-863c-9bcde8a0ffbe")
    public static final String IS_ROOT = "isRoot";

    @objid ("d3f6b092-0881-49df-b61a-2b1d79f92e9f")
    public static final String IS_ROUNDTRIP = "isRoundTrip";

    @objid ("d27a0064-aa8a-4131-9c7d-dcfca55c83ee")
    public static final String IS_SERVICE = "isService";

    @objid ("50701c17-a6f2-4a0a-ba13-ab475aaf4fb6")
    public static final String IS_SIGNATUREPART = "isSignaturePart";

    @objid ("4c939854-39c1-463e-b1a2-02740b94f539")
    public static final String IS_TARGET_IS_CLASS = "isTargetIsClass";

    @objid ("92ede2f5-ad71-4321-bbb4-b2ad3adf0242")
    public static final String IS_UNDEFINED = "isUndefined";

    @objid ("1aac6a46-a6bf-444c-ac3f-3a02b5bac1a0")
    public static final String IS_UNIQUE = "isIsUnique";

    @objid ("0a920184-1d11-4ce0-9606-04902c453496")
    public static final String IS_VALUEPARAMETER = "isValueParameter";

    @objid ("4fc5c248-3ab6-4c81-a2c9-ebd00b4e0de1")
    public static final String JOINSPEC = "JoinSpecification";

    @objid ("90d7e9ba-c8fd-4e1c-9a8a-19cff69ec873")
    public static final String LABEL = "Label";

    @objid ("488e62c6-d31f-4f1c-8eec-2352224024b4")
    public static final String LINENUMBER = "LineNumber";

    @objid ("d24bf07e-df0d-49ac-93d5-0679fa570daf")
    public static final String MODULE = "Module";

    @objid ("a504fa7b-3444-481f-9064-5d53245d3c8b")
    public static final String MULTI_MAX = "MultiMax";

    @objid ("c5d03a6b-4fa3-4ce2-b27d-ff426cffe349")
    public static final String MULTI_MIN = "MultiMin";

    @objid ("5fbafed4-43e1-431d-a0a9-8189d359866d")
    public static final String NOTE_TYPE_NAME = "NoteType_Name";

    @objid ("f7fb38f4-1c1e-4179-86be-7331a7d1e4f1")
    public static final String NUMBER_PROVIDED_INTERFACE = "NumberProvidedInterface";

    @objid ("411d45d5-1743-41b8-ae1d-f4c8b548ceeb")
    public static final String NUMBER_REQUIRED_INTERFACE = "NumberRequiredInterface";

    @objid ("971ba993-de43-4dea-9e2e-1bd123b58477")
    public static final String OWNER = "Owner";

    @objid ("4386395b-3541-4175-bf0b-8ddc1e563306")
    public static final String PORT_DIRECTION = "PortDirection";

    @objid ("333e48eb-b001-4b39-8f43-bda55444e203")
    public static final String POSTCONDITION = "postCondition";

    @objid ("dac83dc7-c173-4108-a0d8-f076ded4a2f2")
    public static final String READ_VALUE = "read";

    @objid ("9fef4068-3a7a-4924-b919-75ca7c01cb9b")
    public static final String READ_WRITE_VALUE = "read_write";

    @objid ("7548542b-3a38-44d2-b03c-2b43d4f7764e")
    public static final String RECEIVED_EVENT = "ReceivedEvent";

    @objid ("2adcd1be-b4ba-4a81-8635-5437f7ae00b3")
    public static final String SIGNAL = "Signal";

    @objid ("880441fe-f875-49f6-8f00-cd6cb2f1bfec")
    public static final String STEREOTYPE = "Stereotype";

    @objid ("5ab31dbd-805a-44f6-a62a-13a458960703")
    public static final String TYPE = "Type";

    @objid ("69e483f3-0198-49c5-aa66-870cfe491600")
    public static final String TYPE_CONSTRAINT = "TypeConstraint";

    @objid ("ed46a5c3-abd9-41d2-b97d-2648f6a36b19")
    public static final String UUID = "UUId";

    @objid ("ffe7eb25-125a-48ee-8077-6a4935150fb0")
    public static final String VALUE = "Value";

    @objid ("eae3b461-79bc-4bab-8611-bd7095afb700")
    public static final String VISIBILITY = "Visibility";

    @objid ("ccfc7a70-d82a-46f6-a540-a05fb0fd0260")
    public static final String WRITE_VALUE = "write";

    @objid ("1bc133a8-da5b-4c59-b8c1-63b05bbd794c")
    public static void addBaseClass(final org.eclipse.uml2.uml.Stereotype ecoreElt, final String baseClass) {
        addEAnnotationContent(ecoreElt, OBJING_NAME, BASECLASS, baseClass);
    }

    @objid ("65487300-ae3c-45c0-a500-da15093318cc")
    public static void addObjingID(final org.eclipse.uml2.uml.Element ecoreElt, final String objingID) {
        addEAnnotationContent(ecoreElt, OBJING_NAME, ELT_ID, objingID);
    }

    @objid ("68729e21-2dbd-4a8d-b386-9aed4824b291")
    public static void alwaysSetOwner(final org.eclipse.uml2.uml.Element ecoreElt, final String ownerId) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME,
                OWNER, ownerId);
    }

    @objid ("12701b71-f126-44a1-a913-621b24c7bffb")
    public static void deleteOwner(final org.eclipse.uml2.uml.Element ecoreElt) {
        deleteEAnnotation(ecoreElt, OBJING_NAME, OWNER);
    }

    @objid ("80b0aa7d-8784-4217-a8ae-0c8b5cfed3c4")
    public static boolean existsConstraintInEA(final EAnnotation eAnnotation, final org.eclipse.uml2.uml.Constraint constraint) {
        String constrName = constraint.getName();
        org.eclipse.uml2.uml.ValueSpecification valueSpecif = constraint.getSpecification();
        if (valueSpecif instanceof org.eclipse.uml2.uml.LiteralString) {
            org.eclipse.uml2.uml.LiteralString stringValue = (org.eclipse.uml2.uml.LiteralString) valueSpecif;
            String constrBody = stringValue.getValue();
        
            for (Object content : eAnnotation.getContents()) {
                if (content instanceof org.eclipse.uml2.uml.Constraint) {
                    org.eclipse.uml2.uml.Constraint currentConstraint = (org.eclipse.uml2.uml.Constraint) content;
                    String currentConstrName = currentConstraint.getName();
                    org.eclipse.uml2.uml.ValueSpecification currentValueSpecif = currentConstraint
                            .getSpecification();
                    if (currentValueSpecif instanceof org.eclipse.uml2.uml.LiteralString) {
                        org.eclipse.uml2.uml.LiteralString currentStringValue = (org.eclipse.uml2.uml.LiteralString) currentValueSpecif;
                        String currentConstrBody = currentStringValue
                                .getValue();
        
                        if (constrName.equals(currentConstrName)
                                && constrBody.equals(currentConstrBody)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @objid ("9a96c0f4-e673-4dd7-86ba-768cedaf016f")
    public static String getAccessMode(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getAndDestroyEAnnotationContent(ecoreElt, OBJING_NAME, ACCESS_MODE);
    }

    @objid ("d3883177-9ece-448a-a478-a6876f59e99f")
    public static Set<String> getBaseClass(final org.eclipse.uml2.uml.Element ecoreElt) {
        Set<String> baseClasses = new HashSet<>();
        for( String  baseClasse : getEAnnotationContents(ecoreElt, OBJING_NAME, BASECLASS)){
            baseClasses.add(baseClasse);
        }
        return baseClasses;
    }

    @objid ("51b0875e-af99-4f40-970f-3864c015eaed")
    public static String getDiagramName(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, DIAGRAMNAME);
    }

    @objid ("8a31e8a4-39ab-4753-ad40-6ded7332f4bf")
    public static String getDiscriminator(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getAndDestroyEAnnotationContent(ecoreElt, OBJING_NAME, DISCRIMINATOR);
    }

    @objid ("148314bb-d11a-49cc-8a12-884653ce58fb")
    public static String getEcoreId(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, ECOREID);
    }

    @objid ("b1dd613e-622c-4784-a43a-62a270a124a2")
    public static String getEffect(final org.eclipse.uml2.uml.Element flow) {
        return getAndDestroyEAnnotationContent(flow, OBJING_NAME, EFFECT);
    }

    @objid ("db3c7fae-774f-4469-b0ea-de52d2d4db78")
    public static int getEndLineNumber(final org.eclipse.uml2.uml.Element ecoreElt) {
        String value = getEAnnotationContent(ecoreElt, OBJING_NAME, ENDLINENUMBER);
        
        if (value.equals("")){
            return 0;
        }else{
            return Integer.valueOf(value);
        }
    }

    @objid ("3be98934-a07f-45cf-9989-1b622531d0f7")
    public static String getIconType(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getAndDestroyEAnnotationContent(ecoreElt, OBJING_NAME, ICONTYPE);
    }

    @objid ("53578894-6c65-42ad-ab7b-a502d1334811")
    public static String getImportedElement(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getAndDestroyEAnnotationContent(ecoreElt, OBJING_NAME, ELT_IMPORT_PATH);
    }

    @objid ("fd58fe7c-d202-4a8e-b473-2a12ca4f6076")
    public static String getJoinSpec(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, JOINSPEC);
    }

    @objid ("d8e43579-acb8-4de0-be36-c95505c21dd6")
    public static String getLabel(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, LABEL);
    }

    @objid ("5e890bb7-3fc0-43d6-a601-fb8dee9be16f")
    public static int getLineNumber(final org.eclipse.uml2.uml.Element ecoreElt) {
        String value = getEAnnotationContent(ecoreElt, OBJING_NAME, LINENUMBER);
        
        if (value.equals("")){
            return 0;
        }else{
            return Integer.valueOf(value);
        }
    }

    @objid ("17a32bb4-fd75-44f1-98d5-9a72132693e7")
    public static String getModule(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, MODULE);
    }

    @objid ("b96091e5-54fe-429e-8067-c2d2664f1b1a")
    public static String getMultiMax(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, MULTI_MAX);
    }

    @objid ("34d4c91c-7ef7-4793-bb7a-0fdb14eb46bc")
    public static String getMultiMin(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, MULTI_MIN);
    }

    @objid ("47d36ddf-64e8-4c04-8761-33420b30bc2b")
    public static String getName(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, ELT_NAME);
    }

    @objid ("78696e65-e00c-48a5-a553-65c9dd1a623a")
    public static String getNoteTypeName(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getAndDestroyEAnnotationContent(ecoreElt, OBJING_NAME, NOTE_TYPE_NAME);
    }

    @objid ("293266c0-cc36-4b92-a9d4-85218c0e69c5")
    public static Integer getNumberProvidedInterface(final org.eclipse.uml2.uml.Element ecoreElt) {
        String temp = getEAnnotationContent(ecoreElt, OBJING_NAME, NUMBER_PROVIDED_INTERFACE);
        if ((temp != null) && (!temp.equals(""))){
            return Integer.valueOf(temp);
        } else {
            return 0;
        }
    }

    @objid ("3179fb03-8d97-4574-a659-1056f3d2e92f")
    public static Integer getNumberRequiredInterface(final org.eclipse.uml2.uml.Element ecoreElt) {
        String temp = getEAnnotationContent(ecoreElt, OBJING_NAME, NUMBER_REQUIRED_INTERFACE);
        if ((temp != null) && (!temp.equals(""))){
            return Integer.valueOf(temp);
        }else {
            return 0;
        }
    }

    @objid ("b718304c-8c2d-4ef4-bc6c-652cd0216f81")
    public static String getObjingEAnnotation(final org.eclipse.uml2.uml.Element ecoreElt, final String name) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME,name);
    }

    @objid ("ba6fe945-8ba7-475d-92f5-b293423306aa")
    public static List<String> getObjingIDs(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContents(ecoreElt, OBJING_NAME, ELT_ID);
    }

    @objid ("1f8acf07-6098-4a19-b17f-b346fb45c455")
    public static EAnnotation getOrCreateObjingEAnnotation(final org.eclipse.uml2.uml.Element ecoreElt) {
        EAnnotation ea = getEAnnotation(ecoreElt, OBJING_NAME);
        if (ea == null){
            ea = createEAnnotation(ecoreElt, OBJING_NAME);
        }
        return ea;
    }

    @objid ("a8e88535-6b25-4698-a242-827d564200c8")
    public static String getOwner(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, OWNER);
    }

    @objid ("ef1f4206-7d00-46c4-89c3-51ab64bc9142")
    public static String getPortDirection(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, PORT_DIRECTION);
    }

    @objid ("12517e85-ccd0-4399-bb59-ba6e3bc1cab1")
    public static String getPostCondition(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, POSTCONDITION);
    }

    @objid ("1a6b984a-5530-4187-a539-25a4d3e97c46")
    public static String getReceivedEvent(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, RECEIVED_EVENT);
    }

    @objid ("e048b324-87ba-4b4c-b185-91f9e4dd8c8b")
    public static boolean getRoundTrip(final org.eclipse.uml2.uml.Element ecoreElt) {
        return hasEAnnotationContent(ecoreElt, OBJING_NAME, IS_ROUNDTRIP);
    }

    @objid ("37c37e92-f731-446e-becf-7b45325cf0d6")
    public static String getSignal(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, SIGNAL);
    }

    @objid ("65caff19-e84f-4cd5-806f-c69c0d7e0be5")
    public static String getStereotype(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, STEREOTYPE);
    }

    @objid ("bd2bf756-f4e3-40ea-a27d-c3f5c28b308c")
    public static String getType(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, TYPE );
    }

    @objid ("114de1cc-de6d-44e9-9c4a-89a8816acf3a")
    public static String getTypeConstraint(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, TYPE_CONSTRAINT);
    }

    @objid ("d48be1dd-fe03-488c-ba9f-a619c497798d")
    public static String getValue(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getEAnnotationContent(ecoreElt, OBJING_NAME, VALUE);
    }

    @objid ("1651fd03-ceb4-47c9-ba23-1fc949f91b9e")
    public static int getVisibility(final org.eclipse.uml2.uml.Element ecoreElt) {
        return Integer.valueOf(getEAnnotationContent(ecoreElt, OBJING_NAME, VISIBILITY));
    }

    @objid ("8d8d96be-3386-4adb-815f-e584ada4cbc5")
    public static boolean hasObjingEAnnotations(final org.eclipse.uml2.uml.Package model) {
        return (getEAnnotation(model, OBJING_NAME) != null);
    }

    @objid ("e40370ec-4d01-4e11-9acd-2fb11ba319ea")
    public static boolean hasObjingID(final org.eclipse.uml2.uml.Element ecoreElt) {
        return hasEAnnotationContent(ecoreElt, OBJING_NAME, ELT_ID);
    }

    @objid ("62597523-0927-4c6e-9dfe-270f24432f3e")
    public static boolean isAttributeLink(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_ATTRIBUTELINK);
    }

    @objid ("36a94d4e-a082-4238-9248-b2dd0cbe8ba3")
    public static boolean isBehavior(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_BEHAVIOR);
    }

    @objid ("6d199c4f-cbab-432b-9da7-279c3cb3c668")
    public static boolean isBindableInstance(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_BINDABLEINSTANCE);
    }

    @objid ("8b9829a2-95e1-4df9-a76d-4b90cc8e2e5e")
    public static boolean isCombineDuplicate(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_COMBINEDUPLICATE);
    }

    @objid ("6b364cb5-1c51-4e2c-9cf1-6256fb148979")
    public static boolean isConcurrent(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_CONCURRENT);
    }

    @objid ("e5170ca2-2084-4629-8dc6-2d10e15f9817")
    public static boolean isConnector(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_CONNECTOR);
    }

    @objid ("ed3310f5-70d5-447d-a897-f210ecb53d44")
    public static boolean isConstant(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_CONSTANT);
    }

    @objid ("53275420-afa3-4af0-853a-80bfe0848ae1")
    public static boolean isDeleted(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_DELETED);
    }

    @objid ("41787eeb-a4ce-4805-a037-834cbd9eae3c")
    public static boolean isEvent(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_EVENT);
    }

    @objid ("3abe8667-545a-4c8c-a283-2ca01d0ceca7")
    public static boolean isException(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_EXCEPTION);
    }

    @objid ("98a11441-ec2f-493d-9f0f-11d5bf89aabb")
    public static boolean isExpansion(final org.eclipse.uml2.uml.Pin pin) {
        return getBooleanContent(pin, IS_EXPANSION);
    }

    @objid ("2f719ee8-2773-4438-bb5c-fbf8184c2e33")
    public static boolean isHidden(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_HIDDEN);
    }

    @objid ("868226b2-72cb-4bde-bc2b-0c7f4c12a430")
    public static boolean isInstantiable(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_INSTANTIABLE);
    }

    @objid ("ec75499a-5382-49a6-958c-7c2031a62c0f")
    public static boolean isIsAbstract(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_ABSTRACT);
    }

    @objid ("2093d8b8-90bc-48e2-97ff-0f5775702988")
    public static boolean isIsOrdered(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_ORDERED);
    }

    @objid ("425f1b90-f89b-4640-8f96-f76a3105aa58")
    public static boolean isIsUnique(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_UNIQUE);
    }

    @objid ("be8d72f6-f38b-41b3-be41-3d3141e70ee7")
    public static boolean isLeaf(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_LEAF);
    }

    @objid ("94ccb371-1ab6-43d6-8894-89a9098cad62")
    public static boolean isLink(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_LINK);
    }

    @objid ("b2bb7d29-3cac-4d23-9073-3b8350bff2a3")
    public static boolean isMain(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_MAIN);
    }

    @objid ("6510363b-b907-46c7-83c2-75a749851ac9")
    public static boolean isNamedWithConvention(final org.eclipse.uml2.uml.Stereotype ecoreElt) {
        return getBooleanContent(ecoreElt, IS_NAMED_WITH_CONVENTION);
    }

    @objid ("4057e3f3-1fd3-4550-8448-4bd23f76b362")
    public static boolean isNavigable(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_NAVIGABLE);
    }

    @objid ("50fc4579-65ab-4878-b9a1-c14521be0117")
    public static boolean isNoType(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_NOTYPE);
    }

    @objid ("64c2cac8-2d61-46ac-8293-72add0eaa247")
    public static boolean isNoteType(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_NOTETYPE);
    }

    @objid ("d0aeae22-06c6-45b0-85b0-cd03647fe8bd")
    public static boolean isOwnedByAssociation(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_OWNED_BY_ASSOCIATION);
    }

    @objid ("cf78af4e-5d47-4aa3-962c-3193bfb6c1e6")
    public static boolean isOwnedByAssociationClass(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_OWNED_BY_ASSOCIATION_CLASS);
    }

    @objid ("9cce251a-0277-4e7d-ac6e-8603caf2e018")
    public static boolean isPartSignature(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_SIGNATUREPART);
    }

    @objid ("d0b9394a-261c-4916-a321-c1e522929e60")
    public static boolean isPort(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_PORT);
    }

    @objid ("04c2a551-4ec4-458d-92c5-58ea29e598db")
    public static boolean isPrimitive(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_ELEMENTARY);
    }

    @objid ("ae48b0cb-2131-4efb-9fe0-36bf749caac7")
    public static boolean isProvidedInterface(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_PROVIDED_INTERFACE);
    }

    @objid ("9f66a26e-670b-41e8-944a-bcaa794067ec")
    public static boolean isReentrant(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_REENTRANT);
    }

    @objid ("26418f72-113d-4b90-a632-16acfbdfd664")
    public static boolean isReference(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_REFERENCE);
    }

    @objid ("e43432ff-8242-45d6-9d2b-e087ac6db3b3")
    public static boolean isRequiredInterface(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_REQUIRED_INTERFACE);
    }

    @objid ("7a1f91cc-7071-46c4-8bca-4416c31d1005")
    public static boolean isRequirementContainer(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_REQUIREMENT_CONTAINER);
    }

    @objid ("c20677b9-7b25-4e87-a982-e48ab6735a2e")
    public static boolean isRoot(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_ROOT);
    }

    @objid ("ed6d6079-73e6-49d5-bcec-08edd4032cde")
    public static boolean isRoundTrip(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_ROUNDTRIP);
    }

    @objid ("e48714dd-bb3f-4515-b1e8-6072232699a8")
    public static boolean isService(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_SERVICE);
    }

    @objid ("d2c9fd02-1839-4336-a78a-54d33d95830e")
    public static boolean isStereotyped(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, STEREOTYPE);
    }

    @objid ("9bffa424-85ec-4497-9294-f4b29b0454b7")
    public static boolean isTargetIsClass(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_TARGET_IS_CLASS);
    }

    @objid ("deeb0984-3d12-4b6c-9c1c-a5250aac8ae2")
    public static boolean isUndefined(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_UNDEFINED);
    }

    @objid ("a8e857ec-5ee1-497a-ae01-12404b657732")
    public static boolean isValueParameter(final org.eclipse.uml2.uml.Element ecoreElt) {
        return getBooleanContent(ecoreElt, IS_VALUEPARAMETER);
    }

    @objid ("e6d60027-8ffb-4b03-b79a-a12d6f66b84c")
    public static void setAccessMode(final org.eclipse.uml2.uml.Element ecoreElt, final String accessMode) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, ACCESS_MODE, accessMode);
    }

    @objid ("745809b5-aab7-4bb5-b10f-d5a3fc50bf83")
    public static void setDiagramName(final org.eclipse.uml2.uml.Element ecoreElt, String name) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, DIAGRAMNAME, name);
    }

    @objid ("9ddf5900-a581-44ae-a57b-07ebb81443fc")
    public static void setDiscriminator(final org.eclipse.uml2.uml.Element ecoreElt, final String discriminator) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, DISCRIMINATOR, discriminator);
    }

    @objid ("d1595b94-f433-48c9-a9cf-fcfdc37b9c30")
    public static void setEcoreId(final org.eclipse.uml2.uml.Element ecoreElt, final String ecoreId) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, ECOREID, ecoreId);
    }

    @objid ("93bb459c-3055-4193-b26d-2b73101e4499")
    public static void setEffect(final org.eclipse.uml2.uml.Element flow, String effect) {
        setOrCreateEAnnotationContent(flow, OBJING_NAME, EFFECT, effect);
    }

    @objid ("f47b53d1-9d7d-4992-8c48-f31b383cb3fd")
    public static void setEndLineNumber(final org.eclipse.uml2.uml.Element ecoreElt, final int endLineNumber) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, ENDLINENUMBER, Integer.toString(endLineNumber));
    }

    @objid ("cd0ae837-bd60-4e3f-be10-4b2816830631")
    public static void setExporterVersion(final org.eclipse.uml2.uml.Element ecoreElt, final String version) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, EXPORTERVERSION, version);
    }

    @objid ("5445313b-3312-4070-8803-2527929617b1")
    public static void setIconType(final org.eclipse.uml2.uml.Element ecoreElt, final String effect) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, ICONTYPE, effect);
    }

    @objid ("68f76e52-c03c-40c1-b18a-f88e9b0c91cd")
    public static void setImportedElement(final org.eclipse.uml2.uml.Element ecoreElt, final String eltPath) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, ELT_IMPORT_PATH, eltPath);
    }

    @objid ("7ad33964-4126-440f-b3be-a6ecc4e90f6a")
    public static void setIsAbstract(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isIsAbstract) {
        if (isIsAbstract) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_ABSTRACT);
        }
    }

    @objid ("f2b82625-a9e4-4400-b679-0f785e8933d9")
    public static void setIsAttributeLink(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_ATTRIBUTELINK);
    }

    @objid ("9467b3d7-856a-4cda-bffa-eaf70df93b14")
    public static void setIsBehavior(final org.eclipse.uml2.uml.Element ecoreElt, boolean isBehavior) {
        if  (isBehavior) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_BEHAVIOR);
        }
    }

    @objid ("b2bcb1f1-3e06-4891-b551-76a95c83923e")
    public static void setIsBindableInstance(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_BINDABLEINSTANCE);
    }

    @objid ("842ac85c-949a-4a7a-86c5-e6188bd9f195")
    public static void setIsCombineDuplicate(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isCombineDuplicate) {
        if (isCombineDuplicate) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_COMBINEDUPLICATE);
        }
    }

    @objid ("d0302e02-7b09-4300-b9f1-63c3f041a797")
    public static void setIsConcurrent(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isConcurrent) {
        if (isConcurrent) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_CONCURRENT);
        }
    }

    @objid ("ba6c78c2-d7e6-491d-9862-c88babbb96f9")
    public static void setIsConnector(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME, IS_CONNECTOR);
    }

    @objid ("9154a462-703c-4fb8-85e5-c877c28516db")
    public static void setIsConstant(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isConstant) {
        if (isConstant) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_CONSTANT);
        }
    }

    @objid ("cbd30e77-94c4-4288-9881-c33ec827f1f2")
    public static void setIsDeleted(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_DELETED);
    }

    @objid ("05882ddf-820f-41bf-a239-a7e80baf2e64")
    public static void setIsEvent(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isEvent) {
        if (isEvent) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_EVENT);
        }
    }

    @objid ("7b536e11-43fe-436d-8131-62db3279304b")
    public static void setIsException(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isException) {
        if (isException) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_EXCEPTION);
        }
    }

    @objid ("2cbc3984-bd4e-4e9f-8745-ad34fdb3ce26")
    public static void setIsExpansion(final org.eclipse.uml2.uml.Pin pin, final boolean isExpansion) {
        if (isExpansion) {
            createEAnnotation(pin, OBJING_NAME,IS_EXPANSION);
        }
    }

    @objid ("52666e1c-2b0c-4dad-87a6-4fe5db5d9762")
    public static void setIsHidden(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isHidden) {
        if (isHidden) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_HIDDEN);
        }
    }

    @objid ("9ddc1750-495c-48fb-84ea-e6af25dfdea9")
    public static void setIsInstantiable(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isInstantiable) {
        if (isInstantiable) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_INSTANTIABLE);
        }
    }

    @objid ("edbfa336-c9d5-42c1-91f0-912c6ba37ff3")
    public static void setIsLeaf(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isLeaf) {
        if (isLeaf) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_LEAF);
        }
    }

    @objid ("b434f238-c3d7-4a5d-a8be-bb3128bf4193")
    public static void setIsLink(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_LINK);
    }

    @objid ("4783ba52-4bd6-4e00-a2b3-1844b701c65d")
    public static void setIsMain(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isMain) {
        if (isMain) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_MAIN);
        }
    }

    @objid ("e422123a-7916-4de5-93f3-14e66dd4784c")
    public static void setIsNamedWithConvention(final org.eclipse.uml2.uml.Stereotype ecoreElt, final boolean isNamedWithConvention) {
        if (isNamedWithConvention) 
            createEAnnotation(ecoreElt, OBJING_NAME,IS_NAMED_WITH_CONVENTION);
    }

    @objid ("8ae0573b-916b-4826-aeb2-94781e0e2834")
    public static void setIsNavigable(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isNavigable) {
        if (isNavigable) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_NAVIGABLE);
        }
    }

    @objid ("7eb28878-5d29-47de-8b45-d43d2a64da31")
    public static void setIsNoType(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_NOTYPE);
    }

    @objid ("03f9153b-994f-484c-83ed-c0f4e5c9166d")
    public static void setIsNoteType(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_NOTETYPE);
    }

    @objid ("c3268a39-807b-4b6c-99cc-3ec1d41ca34d")
    public static void setIsOrdered(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isIsOrdered) {
        if (isIsOrdered) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_ORDERED);
        }
    }

    @objid ("52fd5fcc-10ff-4621-a0c3-9664577025ed")
    public static void setIsOwnedByAssociation(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_OWNED_BY_ASSOCIATION);
    }

    @objid ("11aedf5e-89a6-42a3-9142-cc67a707bab9")
    public static void setIsOwnedByAssociationClass(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_OWNED_BY_ASSOCIATION_CLASS);
    }

    @objid ("b0c77430-5477-43cf-8f65-1ae1e2b7a001")
    public static void setIsPartSignature(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isPartSignature) {
        if (isPartSignature) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_SIGNATUREPART);
        }
    }

    @objid ("0e33881d-2712-4d94-a3f8-68db40be79a7")
    public static void setIsPort(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_PORT);
    }

    @objid ("f3af20a2-91b2-46b5-afe9-74b9134c1b6c")
    public static void setIsPrimitive(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isPrimitive) {
        if (isPrimitive) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_ELEMENTARY);
        }
    }

    @objid ("0d3db433-9850-4d45-84af-844547b11174")
    public static void setIsProvidedInterface(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_PROVIDED_INTERFACE);
    }

    @objid ("8e97a7e7-96d6-4be3-8e59-1b6f8de1eccb")
    public static void setIsReentrant(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isReentrant) {
        if (isReentrant) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_REENTRANT);
        }
    }

    @objid ("9c25467b-624a-4697-80fa-1796ed9e808c")
    public static void setIsReference(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_REFERENCE);
    }

    @objid ("88723b2b-c9c4-4ad4-bbc0-610763c9e7f4")
    public static void setIsRequiredInterface(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_REQUIRED_INTERFACE);
    }

    @objid ("7f6170ac-cd08-4310-ab69-87518d765a74")
    public static void setIsRequirementContainer(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isRequirementContainer) {
        if (isRequirementContainer){
            createEAnnotation(ecoreElt, OBJING_NAME,IS_REQUIREMENT_CONTAINER);
        }
    }

    @objid ("3f0543ca-5370-4586-8e33-d75fdc828462")
    public static void setIsRoot(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isRoot) {
        if (isRoot) {
            createEAnnotation(ecoreElt, OBJING_NAME, IS_ROOT);
        }
    }

    @objid ("598a2f19-d5d5-4e97-a554-f38242716200")
    public static void setIsRoundTrip(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isRoundTrip) {
        if (isRoundTrip){
            createEAnnotation(ecoreElt, OBJING_NAME,IS_ROUNDTRIP);
        }
    }

    @objid ("f5ef2682-060b-4ed4-9a87-b378476c449e")
    public static void setIsService(final org.eclipse.uml2.uml.Element ecoreElt, boolean isService) {
        if (isService) {
            createEAnnotation(ecoreElt, OBJING_NAME, IS_SERVICE);
        }
    }

    @objid ("5717770f-4d72-4ecf-8696-2a76427879b8")
    public static void setIsTargetIsClass(final Property property, final boolean targetIsClass) {
        if (targetIsClass) {
            createEAnnotation(property, OBJING_NAME,IS_TARGET_IS_CLASS);
        }
    }

    @objid ("cc00c5b7-21b7-454a-9a9e-66ee253c421f")
    public static void setIsUndefined(final org.eclipse.uml2.uml.Element ecoreElt) {
        createEAnnotation(ecoreElt, OBJING_NAME,IS_UNDEFINED);
    }

    @objid ("661da255-6f6c-4367-9d46-50ab51c7433e")
    public static void setIsUnique(final org.eclipse.uml2.uml.Element ecoreElt, boolean isIsUnique) {
        if (isIsUnique) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_UNIQUE);
        }
    }

    @objid ("74ffaed2-49e8-4441-b011-d26b7e81eef1")
    public static void setIsValueParameter(final org.eclipse.uml2.uml.Element ecoreElt, final boolean isValueParameter) {
        if (isValueParameter) {
            createEAnnotation(ecoreElt, OBJING_NAME,IS_VALUEPARAMETER);
        }
    }

    @objid ("b5de5362-4aae-4ed3-a37f-c6b20894edbf")
    public static void setJoinSpec(final org.eclipse.uml2.uml.Element ecoreElt, final String value) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, JOINSPEC, value);
    }

    @objid ("20bfd333-1d2e-45eb-a753-ef2878be17f1")
    public static void setLabel(final org.eclipse.uml2.uml.Element ecoreElt, final String label) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, LABEL, label);
    }

    @objid ("789b945d-7fec-47d9-81f7-743bb88f7766")
    public static void setLineNumber(final org.eclipse.uml2.uml.Element ecoreElt, final int lineNumber) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, LINENUMBER, Integer.toString(lineNumber));
    }

    @objid ("1b592818-ba14-4d78-82ca-8b28a417e441")
    public static void setModule(final org.eclipse.uml2.uml.Profile ecoreElt, final String module) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, MODULE, module);
    }

    @objid ("d3994cc9-8a45-4ad8-b435-0937ae536ed8")
    public static void setMultiMax(final org.eclipse.uml2.uml.Element ecoreElt, final String multiMax) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, MULTI_MAX, multiMax);
    }

    @objid ("f8d3e9a0-d807-47e9-8ea0-22ea0cfb1b2a")
    public static void setMultiMin(final org.eclipse.uml2.uml.Element ecoreElt, final String multiMin) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, MULTI_MIN, multiMin);
    }

    @objid ("8709ab72-0481-4e48-bece-b57652000e10")
    public static void setName(final org.eclipse.uml2.uml.Element ecoreElt, final String name) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, ELT_NAME, name);
    }

    @objid ("0f3d4034-2c78-4b59-87a5-8a68bbfe1c7d")
    public static void setNoteTypeName(final org.eclipse.uml2.uml.Element ecoreElt, final String value) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, NOTE_TYPE_NAME, value);
    }

    @objid ("a8dd3025-63fb-47b8-a461-ae04a019ca8e")
    public static void setNumberProvidedInterface(final org.eclipse.uml2.uml.Element ecoreElt, final Integer value) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, NUMBER_PROVIDED_INTERFACE,  String.valueOf(value));
    }

    @objid ("96c69007-690a-4624-b266-f98f6c0d7151")
    public static void setNumberRequiredInterface(final org.eclipse.uml2.uml.Element ecoreElt, final Integer value) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, NUMBER_REQUIRED_INTERFACE, String.valueOf(value));
    }

    @objid ("0b257e1d-df97-4084-b61b-366f22645f98")
    public static void setOwner(final org.eclipse.uml2.uml.Element ecoreElt, final String ownerId) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, OWNER, ownerId);
    }

    @objid ("881abab8-60b5-4824-a19b-b0507bbc9b8d")
    public static void setPortDirection(final org.eclipse.uml2.uml.Element ecoreElt, final String portDirection) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, PORT_DIRECTION, portDirection);
    }

    @objid ("0df51b18-a92e-4c79-b6b5-2869c6668293")
    public static void setPostCondition(final org.eclipse.uml2.uml.Element ecoreElt, final String value) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, POSTCONDITION, value);
    }

    @objid ("a2f4803e-308d-4e44-96c6-457115aa58c8")
    public static void setReceivedEvent(final org.eclipse.uml2.uml.Element ecoreElt, final String value) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, RECEIVED_EVENT, value);
    }

    @objid ("3e0dd494-bcd7-48eb-b1c2-ad82f65dafc9")
    public static void setSignal(final org.eclipse.uml2.uml.Element ecoreElt, final String value) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, SIGNAL, value);
    }

    @objid ("f32ed5fb-9a7b-4efe-a121-e17347e87e9e")
    public static void setType(final org.eclipse.uml2.uml.Element ecoreElt, final String type) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, TYPE, type);
    }

    @objid ("f510e369-ffe0-4ee5-b67e-013d88ef3d30")
    public static void setTypeConstraint(final org.eclipse.uml2.uml.Element ecoreElt, final String typeConstraint) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, TYPE_CONSTRAINT, typeConstraint);
    }

    @objid ("7f948841-914b-40ef-a997-c6e057cf2a09")
    public static void setUUId(final org.eclipse.uml2.uml.Element ecoreElt, final String identifier) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, UUID, identifier);
    }

    @objid ("f4ac5edf-70a5-4412-a354-2b2850837c32")
    public static void setValue(final org.eclipse.uml2.uml.Element ecoreElt, final String effect) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, VALUE, effect);
    }

    @objid ("ae9d424b-2880-4337-9528-4dd67e2b03fd")
    public static void setVisibility(final org.eclipse.uml2.uml.Element ecoreElt, final int enume) {
        setOrCreateEAnnotationContent(ecoreElt, OBJING_NAME, VISIBILITY, String.valueOf(enume));
    }

}
