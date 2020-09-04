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
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
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
 * Proxy class to handle a {@link MethodologicalLink} with << PartitionElement >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Acteurs, rôles : drag & drop dans les lanes & pool permettent de les associer et faire apparaitre. Les icones (ArchiMate) doivent s’afficher par défaut.</i></p>
 */
@objid ("de449f4b-02f1-4a89-aee5-f8ce3007641b")
public class PartitionElement {
    @objid ("fe171778-f883-4de9-bb5f-6460204e6e5e")
    public static final String STEREOTYPE_NAME = "PartitionElement";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("ab24ef00-7219-48cb-9e3b-25ec0ad871dc")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link PartitionElement proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << PartitionElement >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e19e71d1-1ab0-4ad8-80b8-12a1dbff123b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PartitionElement.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << PartitionElement >> then instantiate a {@link PartitionElement} proxy.
     * 
     * @return a {@link PartitionElement} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("1395452a-568a-40b2-b8ef-4d9deb68feed")
    public static PartitionElement create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PartitionElement.STEREOTYPE_NAME);
        return PartitionElement.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link PartitionElement} proxy from a {@link MethodologicalLink} stereotyped << PartitionElement >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link PartitionElement} proxy or <i>null</i>.
     */
    @objid ("0cf18f4c-6295-46b4-8f07-133078f210fd")
    public static PartitionElement instantiate(MethodologicalLink obj) {
        return PartitionElement.canInstantiate(obj) ? new PartitionElement(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PartitionElement} proxy from a {@link MethodologicalLink} stereotyped << PartitionElement >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link PartitionElement} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("20382b4a-fd72-4ecc-9ece-610a0d5a9e93")
    public static PartitionElement safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (PartitionElement.canInstantiate(obj))
        	return new PartitionElement(obj);
        else
        	throw new IllegalArgumentException("PartitionElement: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("91331fb1-fdcc-401b-9b23-a8272f6184f5")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("67e8e257-20b2-4f61-9cba-f089e9bc439f")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("7d5f468d-2e9e-4d2c-9639-b4dcde892448")
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
        PartitionElement other = (PartitionElement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("f968cffd-36e7-4d04-bbdb-5740a8d815ea")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("aa572f75-3d59-4dd4-a226-cefcb6777542")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("44f5b928-1195-4a13-8b48-be59673f49a9")
    protected PartitionElement(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("5e6cefd6-55cf-4f85-b0bc-77215412fc10")
    public static final class MdaTypes {
        @objid ("5a7f84a1-666a-49ab-9c6f-9af66ac45404")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("077531e1-08f8-4e32-a8a0-d778cd2f5d3f")
        private static Stereotype MDAASSOCDEP;

        @objid ("3c84c8cb-c60c-4386-b2cb-c6aa5adacc9f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0ef52a16-3055-457f-940d-48117451a92e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5de33d2a-ed28-439c-aa09-d11bf1a6d878");
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
