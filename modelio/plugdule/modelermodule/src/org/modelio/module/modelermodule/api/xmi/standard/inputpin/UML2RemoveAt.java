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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2RemoveAt >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e9bdfdee-c4b7-4b08-8bf3-1518a048df86")
public class UML2RemoveAt {
    @objid ("95267bf1-247a-43bc-aafc-d67c168fcbc4")
    public static final String STEREOTYPE_NAME = "UML2RemoveAt";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("c314581a-0c39-46b9-8d84-0a3c205a4d3c")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2RemoveAt proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2RemoveAt >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("49726c54-469c-4b74-885e-4e09064e7e6c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RemoveAt.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2RemoveAt >> then instantiate a {@link UML2RemoveAt} proxy.
     * 
     * @return a {@link UML2RemoveAt} proxy on the created {@link InputPin}.
     */
    @objid ("9efb8743-362a-4a7f-a13f-4da809290ce1")
    public static UML2RemoveAt create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RemoveAt.STEREOTYPE_NAME);
        return UML2RemoveAt.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2RemoveAt} proxy from a {@link InputPin} stereotyped << UML2RemoveAt >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2RemoveAt} proxy or <i>null</i>.
     */
    @objid ("dd27056d-eccd-48e6-a43c-d9d2a984413c")
    public static UML2RemoveAt instantiate(InputPin obj) {
        return UML2RemoveAt.canInstantiate(obj) ? new UML2RemoveAt(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RemoveAt} proxy from a {@link InputPin} stereotyped << UML2RemoveAt >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2RemoveAt} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("12d0852a-da0e-470d-ac7c-80cf7badfac7")
    public static UML2RemoveAt safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2RemoveAt.canInstantiate(obj))
        	return new UML2RemoveAt(obj);
        else
        	throw new IllegalArgumentException("UML2RemoveAt: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a0ab89f0-d6f1-42f0-b363-9d63951bb3d1")
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
        UML2RemoveAt other = (UML2RemoveAt) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("548aa2b6-3808-4e73-9117-16a723a33ded")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("a4964c73-e232-41c0-9e07-ebce75c98c76")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("901254ff-9234-4652-8d27-7d1a0b8880be")
    protected UML2RemoveAt(InputPin elt) {
        this.elt = elt;
    }

    @objid ("140353cd-0401-48a1-a01c-d3f021559f83")
    public static final class MdaTypes {
        @objid ("3dd2e350-fa65-4024-a76c-fecea6466cf4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1389d9be-4d30-4339-90f6-d9c4b7d23e39")
        private static Stereotype MDAASSOCDEP;

        @objid ("f22ff5a5-8cdf-4d87-b43c-171c6ffda255")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("45c1e0aa-f27e-40a5-a72c-6d7b0c427399")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "407d1bab-d29f-4f92-b12f-01283c1cc7eb");
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
