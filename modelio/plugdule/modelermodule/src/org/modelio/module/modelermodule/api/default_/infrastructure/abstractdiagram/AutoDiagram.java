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

/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
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
 * Proxy class to handle a {@link AbstractDiagram} with << AutoDiagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b410fcf6-31df-4c16-b0e5-ff41e19d9852")
public class AutoDiagram {
    @objid ("22e63a7a-9cf6-4a91-a27d-6e1898b0feb0")
    public static final String STEREOTYPE_NAME = "AutoDiagram";

    /**
     * The underlying {@link AbstractDiagram} represented by this proxy, never null.
     */
    @objid ("57e1ebd6-60d6-48c1-9556-7bc7197856a6")
    protected final AbstractDiagram elt;

    /**
     * Tells whether a {@link AutoDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link AbstractDiagram} stereotyped << AutoDiagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c8b9fa88-3741-4254-b1d8-e2b33e89b1a5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof AbstractDiagram) && ((AbstractDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, AutoDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link AbstractDiagram} stereotyped << AutoDiagram >> then instantiate a {@link AutoDiagram} proxy.
     * 
     * @return a {@link AutoDiagram} proxy on the created {@link AbstractDiagram}.
     */
    @objid ("acd1200a-e6e9-4399-b6b7-8b31d8a6dd40")
    public static AutoDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("AbstractDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, AutoDiagram.STEREOTYPE_NAME);
        return AutoDiagram.instantiate((AbstractDiagram)e);
    }

    /**
     * Tries to instantiate a {@link AutoDiagram} proxy from a {@link AbstractDiagram} stereotyped << AutoDiagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a AbstractDiagram
     * @return a {@link AutoDiagram} proxy or <i>null</i>.
     */
    @objid ("26192001-97dd-4032-af2b-37c95dbe7279")
    public static AutoDiagram instantiate(AbstractDiagram obj) {
        return AutoDiagram.canInstantiate(obj) ? new AutoDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link AutoDiagram} proxy from a {@link AbstractDiagram} stereotyped << AutoDiagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link AbstractDiagram}
     * @return a {@link AutoDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("bdeafaae-4724-4a56-8287-eb445afc5e94")
    public static AutoDiagram safeInstantiate(AbstractDiagram obj) throws IllegalArgumentException {
        if (AutoDiagram.canInstantiate(obj))
        	return new AutoDiagram(obj);
        else
        	throw new IllegalArgumentException("AutoDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6d0f5d86-b29e-4ebb-9ea7-254e47d6c95c")
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
        AutoDiagram other = (AutoDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link AbstractDiagram}. 
     * @return the AbstractDiagram represented by this proxy, never null.
     */
    @objid ("6907f5b3-395c-41ba-a69b-5044f397a89c")
    public AbstractDiagram getElement() {
        return this.elt;
    }

    @objid ("96eec34e-96f4-4454-9778-294d8d4380a0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("81b062b7-113d-44f2-bdf7-d03dfa4f3b91")
    protected AutoDiagram(AbstractDiagram elt) {
        this.elt = elt;
    }

    @objid ("1d38733b-2cc3-4edc-aec0-6c87d5b6b705")
    public static final class MdaTypes {
        @objid ("be61005b-0936-4803-84cb-cc1bfcec5a50")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("dbfdfbe2-473f-41f4-aed6-6a9d86e4236c")
        private static Stereotype MDAASSOCDEP;

        @objid ("df692e88-0a32-49bf-bfd3-0813c51a8040")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("57eb647b-8af2-4537-8016-b8c9ee0328f2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8fb43b1c-7819-11e1-a4f1-002564c97630");
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
