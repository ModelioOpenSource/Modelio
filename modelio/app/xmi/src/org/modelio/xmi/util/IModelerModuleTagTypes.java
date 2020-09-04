/* 
 * Copyright 2013-2018 Modeliosoft
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

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9c50bd54-64d6-41d8-aaf2-b357da9da897")
public interface IModelerModuleTagTypes {
    @objid ("d9c50808-fd86-4259-b0e9-57e9efc81f86")
    public static final String ABSTRACTDIAGRAM_GENERATEDDIAGRAM = "generatedDiagram";

    @objid ("105e0faf-7351-4c0d-976b-874383864d71")
    public static final String ASSOCIATIONEND_NOCODE = "nocode";

    @objid ("152ee581-9e01-4e3f-a662-a0fb1b219aa6")
    public static final String ASSOCIATIONEND_ORDERED = "ordered";

    @objid ("0b50c8e9-c9be-4705-8c7e-a25f8d71c42e")
    public static final String ASSOCIATIONEND_QUALIFIER = "qualifier";

    @objid ("0e910e81-5990-4875-b465-b700cdb12339")
    public static final String ASSOCIATIONEND_TYPE = "type";

    @objid ("1c6beaf3-bd45-426e-9191-95988abf1e73")
    public static final String ASSOCIATION_PERSISTENCE = "persistence";

    @objid ("c8261a14-3226-43f2-94df-ccbbb73c3e6e")
    public static final String ATTRIBUTE_NOCODE = "nocode";

    @objid ("d301824a-54b2-4a0a-bdb8-5c6419f0d0d9")
    public static final String ATTRIBUTE_PERSISTENCE = "persistence";

    @objid ("571821a6-e545-436f-81e7-7eafd67d7591")
    public static final String ATTRIBUTE_TYPE = "type";

    @objid ("4e9b1866-b303-445f-aa8d-dd8f8d48deab")
    public static final String CLASSIFIER_PERSISTENCE = "persistence";

    @objid ("948d8bcb-9082-453a-8554-3be86d50339e")
    public static final String DEPENDENCY_CAUSE_DEPTH = "cause_depth";

    @objid ("20f490da-6595-409d-baee-f6737610f035")
    public static final String DEPENDENCY_CONSEQUENCE_DEPTH = "consequence_depth";

    @objid ("d6dc553f-278c-43be-b439-e64ad8ac28ca")
    public static final String EXTERNALDOCUMENT_ISLINK = "isLink";

    @objid ("7af055ba-c07d-40c3-bdd7-fd79c21b6db7")
    public static final String GENERALCLASS_NOCODE = "nocode";

    @objid ("07d50910-b0b1-46d2-bbb5-ad4fae5e2c53")
    public static final String MODELCOMPONENTARCHIVE_MODELCOMPONENTFILES = "ModelComponentFiles";

    @objid ("b230e980-64b4-4906-b4e4-df947db4cd44")
    public static final String MODELCOMPONENTARCHIVE_MODELCOMPONENTVERSION = "ModelComponentVersion";

    @objid ("b23fe7c3-7bbf-4c58-9b5f-ea265060e4e5")
    public static final String NOTE_JEVALUATION = "JEvaluation";

    @objid ("2de8bd1f-3159-40e8-b16a-94c23d29bca6")
    public static final String OPERATION_NOCODE = "nocode";

    @objid ("619c0bea-dfa9-47b1-af72-ee3e020b3498")
    public static final String PACKAGE_NOCODE = "nocode";

    @objid ("9d8d9477-7042-409d-857a-3f544b03f4b9")
    public static final String PARAMETER_TYPE = "type";

    @objid ("9236bc8d-044b-405f-acb4-959911bc31e7")
    public static final String EXTERNALDOCUMENT_LINKLABEL = "LinkLabel";

    @objid ("56c8a9d6-7af1-4d18-907a-32cd6789e95a")
    public static final String REQUIREMENT_EDITIONSIZE = "editionsize";

    @objid ("673fcd3a-450d-43e5-9563-fc1bb660a8d5")
    public static final String TERM_EDITIONSIZE = "editionsize";

    @objid ("77d7fb1a-acf4-4f86-b8ad-cf0c1823e7fe")
    public static final String MODELELEMENT_XMIIMPORTED = "XMIImported";

    @objid ("7a87e988-17cf-4592-aefc-e1780a8622ff")
    public static final String ASSOCIATIONEND_XMIISOWNEDBYCLASSIFIER = "isOwnedByClassifier";

    @objid ("6b40976c-bd5b-46b7-85bc-501e563cd30f")
    public static final String MODELELEMENT_ECOREID = "EcoreId";

    @objid ("6d6613c8-bf66-43bf-a05b-cd15c9d059d1")
    public static final String MODELELEMENT_NOTEXPORTED = "NotExported";

    @objid ("eee27657-4d9f-45d9-833a-a1aec805ea67")
    public static final String UML2ENDDESTRUCTIONDATAREFERENCE_ISDESTROYDUPLICATES = "IsDestroyDuplicates";

    @objid ("079be6e2-24e6-4ad0-9665-118def8a3fe7")
    public static final String UML2EXPANSIONREGION_MODE = "Mode";

    @objid ("e255832d-fda8-457d-b57c-39ed437d5a1a")
    public static final String UML2GENERALIZATIONSET_ID = "Id";

    @objid ("8e65f834-8946-4fb8-9f05-ba0620e70ba3")
    public static final String UML2STEREOTYPEPROPERTY_PROPERTY = "Property";

    @objid ("3bc6b322-9d88-45f2-bf04-b807684c912a")
    public static final String UML2PROPERTYTYPE_TYPE = "Type";

    @objid ("9500708e-fc7f-4285-8e64-c9b07340a5d5")
    public static final String UML2VALUESPECIFICATIONACTION_VALUE = "Value";

    @objid ("3a72ba0d-109f-479e-b143-b1e7c348f7fa")
    public static final String UML2VARIABLE_ORDERED = "Ordered";

    @objid ("8f3ec8b8-b8b9-4d9a-8c25-624abad625c9")
    public static final String UML2VARIABLE_UNIQUE = "Unique";

    @objid ("dd4e3de8-70b9-488b-a756-53d548d3b332")
    public static final String UML2VARIABLE_LOWER = "Lower";

}
