/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.module.modelermodule.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("ca29504e-980e-4814-b136-a025556721ee")
public interface IModelerModuleTagTypes {
    @objid ("0d84853e-f749-4203-bc88-41cb82307283")
    public static final String ASSOCIATIONEND_NOCODE = "nocode";

    @objid ("0b2437be-7cbe-422a-91d6-fbc218c0b2da")
    public static final String ASSOCIATIONEND_ORDERED = "ordered";

    @objid ("a7cb5154-76d6-4f8b-bbe1-056b49988bcd")
    public static final String ASSOCIATIONEND_QUALIFIER = "qualifier";

    @objid ("9afe08d7-0f5f-4c2a-8711-424efd9d4e53")
    public static final String ASSOCIATIONEND_TYPE = "type";

    @objid ("73fdf771-dc14-423b-b439-07ce8cbfc20d")
    public static final String ASSOCIATIONEND_XMIISOWNEDBYCLASSIFIER = "isOwnedByClassifier";

    @objid ("7bef3962-54c6-4713-8581-ec6890ba0083")
    public static final String ASSOCIATION_PERSISTENCE = "persistence";

    @objid ("858c3bef-51a3-4c37-8aec-a48c7bf92e94")
    public static final String ATTRIBUTE_NOCODE = "nocode";

    @objid ("fac8511b-6bf8-42e7-9857-43d0e4b90406")
    public static final String ATTRIBUTE_PERSISTENCE = "persistence";

    @objid ("7077b438-caed-410d-b725-f583fdbde95e")
    public static final String ATTRIBUTE_TYPE = "type";

    @objid ("81435724-64c1-4b0b-8734-7b512beb1a24")
    public static final String CLASSIFIER_PERSISTENCE = "persistence";

    @objid ("41d22aba-cf37-4262-932d-dc1e287f9112")
    public static final String DEPENDENCY_CAUSE_DEPTH = "cause_depth";

    @objid ("a104e959-c8c5-436e-afe1-2a9bdd9f912d")
    public static final String DEPENDENCY_CONSEQUENCE_DEPTH = "consequence_depth";

    @objid ("91a9fc7a-d827-4c46-8e2c-69f61475c9c4")
    public static final String EXTERNALDOCUMENT_ISLINK = "isLink";

    @objid ("0f7ee56f-e608-4b24-a352-4e1315888ce3")
    public static final String EXTERNALDOCUMENT_LINKLABEL = "LinkLabel";

    @objid ("c5ef792c-21ee-49d8-ae44-4fb98fb79351")
    public static final String GENERALCLASS_NOCODE = "nocode";

    @objid ("459ae1cd-e31f-4b74-8261-11207367e23c")
    public static final String MODELCOMPONENTARCHIVE_MODELCOMPONENTFILES = "ModelComponentFiles";

    @objid ("5876ea13-07ee-44a7-a437-319d0d150352")
    public static final String MODELCOMPONENTARCHIVE_MODELCOMPONENTVERSION = "ModelComponentVersion";

    @objid ("b8f58023-9981-4d4a-90c0-38c866b8b713")
    public static final String MODELELEMENT_ECOREID = "EcoreId";

    @objid ("a35064bd-e5bf-44f2-82f8-b6ddc7c0a94a")
    public static final String MODELELEMENT_NODOC = "nodoc";

    @objid ("1f7b8175-bbb3-4831-a886-ff78f80ac00b")
    public static final String MODELELEMENT_NOTEXPORTED = "NotExported";

    @objid ("cad10998-3009-4fbe-9157-575651fbaccc")
    public static final String MODELELEMENT_XMIIMPORTED = "XMIImported";

    @objid ("9d74b9e9-241a-46b3-8025-641c6c9bdfa3")
    public static final String OPERATION_NOCODE = "nocode";

    @objid ("84e334fe-6d82-4612-8b23-1c54de8e9a02")
    public static final String PACKAGE_NOCODE = "nocode";

    @objid ("7857665b-85fa-41d3-b77a-c63845a3e8ee")
    public static final String PARAMETER_TYPE = "type";

    @objid ("a1563487-7fcb-4b29-93b4-30b4f27d84ea")
    public static final String REQUIREMENT_EDITIONSIZE = "editionsize";

    @objid ("d11fd730-9b12-4da3-8ff4-52577e1d8d77")
    public static final String TERM_EDITIONSIZE = "editionsize";

    @objid ("c0c4e2b9-8b8b-4bcd-bdc8-d00c28d88978")
    public static final String UML2ENDDESTRUCTIONDATAREFERENCE_ISDESTROYDUPLICATES = "IsDestroyDuplicates";

    @objid ("d474279a-5b26-44b4-938d-37c562bd9d5d")
    public static final String UML2EXPANSIONREGION_MODE = "Mode";

    @objid ("83eaa6f6-875e-4c26-9e73-97d678123e3a")
    public static final String UML2GENERALIZATIONSET_ID = "Id";

    @objid ("d2d44bda-0b2e-4530-9494-d3a06f1ea1fc")
    public static final String UML2PROPERTYTYPE_TYPE = "Type";

    @objid ("1b2940a5-366d-4160-9a83-d897213472a0")
    public static final String UML2STEREOTYPEPROPERTY_PROPERTY = "Property";

    @objid ("8722006f-23b6-4dd5-ba91-52af7866e3cb")
    public static final String UML2VALUESPECIFICATIONACTION_VALUE = "Value";

    @objid ("a8bf961d-e1a8-4516-864c-584916a26b6a")
    public static final String UML2VARIABLE_LOWER = "Lower";

    @objid ("9f4059e3-06e8-4514-910a-ee31045d7499")
    public static final String UML2VARIABLE_ORDERED = "Ordered";

    @objid ("53452355-a999-404a-ab28-0896a979e71c")
    public static final String UML2VARIABLE_UNIQUE = "Unique";

}
