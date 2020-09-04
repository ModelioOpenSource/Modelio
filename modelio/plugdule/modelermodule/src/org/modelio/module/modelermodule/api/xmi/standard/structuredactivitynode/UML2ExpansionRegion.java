/* 
 * Copyright 2013-2019 Modeliosoft
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

/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link StructuredActivityNode} with << UML2ExpansionRegion >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("7e99c219-46f9-4244-8fb2-b762819d70db")
public class UML2ExpansionRegion {
    @objid ("6ca5e492-eeb3-4ea6-a73d-49bcef3f5b59")
    public static final String STEREOTYPE_NAME = "UML2ExpansionRegion";

    @objid ("b6c71903-20a0-408d-a4f2-9893759f6531")
    public static final String MODE_TAGTYPE = "Mode";

    /**
     * The underlying {@link StructuredActivityNode} represented by this proxy, never null.
     */
    @objid ("4e094653-5dcf-4a39-b2cb-5866a7804c15")
    protected final StructuredActivityNode elt;

    /**
     * Tells whether a {@link UML2ExpansionRegion proxy} can be instantiated from a {@link MObject} checking it is a {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("be5c7233-472b-410e-a013-198719e80212")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StructuredActivityNode) && ((StructuredActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionRegion.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >> then instantiate a {@link UML2ExpansionRegion} proxy.
     * 
     * @return a {@link UML2ExpansionRegion} proxy on the created {@link StructuredActivityNode}.
     */
    @objid ("f64c4485-a5c6-442e-81ec-ca23c0bbc1de")
    public static UML2ExpansionRegion create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StructuredActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionRegion.STEREOTYPE_NAME);
        return UML2ExpansionRegion.instantiate((StructuredActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionRegion} proxy from a {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StructuredActivityNode
     * @return a {@link UML2ExpansionRegion} proxy or <i>null</i>.
     */
    @objid ("55586c64-262d-49fa-877d-418b75c5adba")
    public static UML2ExpansionRegion instantiate(StructuredActivityNode obj) {
        return UML2ExpansionRegion.canInstantiate(obj) ? new UML2ExpansionRegion(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionRegion} proxy from a {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StructuredActivityNode}
     * @return a {@link UML2ExpansionRegion} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fdc80678-d14c-4572-b457-f4392fa3de2b")
    public static UML2ExpansionRegion safeInstantiate(StructuredActivityNode obj) throws IllegalArgumentException {
        if (UML2ExpansionRegion.canInstantiate(obj))
        	return new UML2ExpansionRegion(obj);
        else
        	throw new IllegalArgumentException("UML2ExpansionRegion: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9694661f-fb1a-4ee4-a3e0-9ceee8306213")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UML2ExpansionRegion other = (UML2ExpansionRegion) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StructuredActivityNode}. 
     * @return the StructuredActivityNode represented by this proxy, never null.
     */
    @objid ("ae87c4e4-1b5b-401f-8878-91878fcdc405")
    public StructuredActivityNode getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Mode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("20a8f8bd-ed79-4bca-937a-d680af2f6691")
    public String getMode() {
        return this.elt.getTagValue(UML2ExpansionRegion.MdaTypes.MODE_TAGTYPE_ELT);
    }

    @objid ("e844c1a2-971f-4276-aca7-f4bfa5d7345c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'Mode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("2ee798dd-05a1-4257-8fd4-be81ecc258e5")
    public void setMode(String value) {
        this.elt.putTagValue(UML2ExpansionRegion.MdaTypes.MODE_TAGTYPE_ELT, value);
    }

    @objid ("f53291bc-21bd-4fa6-b4b8-608657f0e636")
    protected UML2ExpansionRegion(StructuredActivityNode elt) {
        this.elt = elt;
    }

    @objid ("1ec99520-b74d-45f5-9edb-594801c80054")
    public static final class MdaTypes {
        @objid ("b3c8fb57-03bd-4bea-a1c9-f06c3551f96b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4f8a4ab3-13f9-4ee2-897f-17b546b352fe")
        public static TagType MODE_TAGTYPE_ELT;

        @objid ("b83239a8-b41d-47a9-9cb1-a67c06d6f661")
        private static Stereotype MDAASSOCDEP;

        @objid ("0422aa47-3c06-4314-a022-a0b3512d10c0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a7750f1a-aebb-41ae-8747-f18d997f9d52")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "07111319-1fd7-11df-948e-001302895b2b");
            MODE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "5930231e-3117-11df-b4ad-001302895b2b");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
