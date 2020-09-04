/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.association;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Association} with << implicit >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a2291f1c-9e40-4062-b1f6-c4ef88ee02eb")
public class Implicit {
    @objid ("b75a98d5-d1e0-4ea7-a560-45081e6f3da7")
    public static final String STEREOTYPE_NAME = "implicit";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("fadecf8d-8cf3-4848-b84a-472814834137")
    protected final Association elt;

    /**
     * Tells whether a {@link Implicit proxy} can be instantiated from a {@link MObject} checking it is a {@link Association} stereotyped << implicit >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c3f51c67-6b06-45dc-b416-8925f2f2cf1d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Association) && ((Association) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Implicit.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Association} stereotyped << implicit >> then instantiate a {@link Implicit} proxy.
     * 
     * @return a {@link Implicit} proxy on the created {@link Association}.
     */
    @objid ("2d105df8-5cb2-4f08-997d-959a54b119fb")
    public static Implicit create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Association");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Implicit.STEREOTYPE_NAME);
        return Implicit.instantiate((Association)e);
    }

    /**
     * Tries to instantiate a {@link Implicit} proxy from a {@link Association} stereotyped << implicit >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link Implicit} proxy or <i>null</i>.
     */
    @objid ("e21f3b23-c7de-48f3-8103-40ed8adbda21")
    public static Implicit instantiate(Association obj) {
        return Implicit.canInstantiate(obj) ? new Implicit(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Implicit} proxy from a {@link Association} stereotyped << implicit >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Association}
     * @return a {@link Implicit} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ecba7367-a5c3-4144-bc91-cd73ad09d46c")
    public static Implicit safeInstantiate(Association obj) throws IllegalArgumentException {
        if (Implicit.canInstantiate(obj))
        	return new Implicit(obj);
        else
        	throw new IllegalArgumentException("Implicit: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("97f148e9-60f4-49b3-858e-e3a16c109d9b")
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
        Implicit other = (Implicit) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Association}. 
     * @return the Association represented by this proxy, never null.
     */
    @objid ("24b6d561-5183-4438-ab6d-36a2018d3c25")
    public Association getElement() {
        return this.elt;
    }

    @objid ("ba41a6ae-23dd-4143-ab4b-b5fbc7999b72")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0c80a289-0c09-4b1b-ba39-4a90272e66b3")
    protected Implicit(Association elt) {
        this.elt = elt;
    }

    @objid ("6e6fdf9c-fd6f-4767-bf30-7a823cf4cd32")
    public static final class MdaTypes {
        @objid ("0310f272-7bc0-4864-959e-a73be58ac48b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("336cdc19-2a88-4c1d-989b-d944f19d0ddf")
        private static Stereotype MDAASSOCDEP;

        @objid ("55c80834-d34d-4318-9c30-0b6e5cab1e9d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fae6eba0-50df-4535-a4d9-a4ba2d5c524a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01b8-0000-000000000000");
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
