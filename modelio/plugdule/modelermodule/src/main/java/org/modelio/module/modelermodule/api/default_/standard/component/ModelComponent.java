/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.component;

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
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Component} with << ModelComponent >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c387e5c6-b04b-4bbe-a77e-ab653b1d5ee1")
public class ModelComponent {
    @objid ("c0e74cf8-bb24-4c1d-b953-183b7ee75002")
    public static final String STEREOTYPE_NAME = "ModelComponent";

    /**
     * The underlying {@link Component} represented by this proxy, never null.
     */
    @objid ("7545fc02-5ac2-4d90-8e35-da5836a1e201")
    protected final Component elt;

    /**
     * Tells whether a {@link ModelComponent proxy} can be instantiated from a {@link MObject} checking it is a {@link Component} stereotyped << ModelComponent >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1e7adb13-b11c-4408-86e6-17cc470f134f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Component) && ((Component) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ModelComponent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Component} stereotyped << ModelComponent >> then instantiate a {@link ModelComponent} proxy.
     * 
     * @return a {@link ModelComponent} proxy on the created {@link Component}.
     */
    @objid ("a5dc6014-5418-45c2-8e89-1ab03dbc454b")
    public static ModelComponent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Component");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ModelComponent.STEREOTYPE_NAME);
        return ModelComponent.instantiate((Component)e);
    }

    /**
     * Tries to instantiate a {@link ModelComponent} proxy from a {@link Component} stereotyped << ModelComponent >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Component
     * @return a {@link ModelComponent} proxy or <i>null</i>.
     */
    @objid ("9d05374d-0c00-471b-b7ff-93dd8c064ea6")
    public static ModelComponent instantiate(Component obj) {
        return ModelComponent.canInstantiate(obj) ? new ModelComponent(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ModelComponent} proxy from a {@link Component} stereotyped << ModelComponent >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Component}
     * @return a {@link ModelComponent} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("06ba45cf-9581-4636-8c52-acec997efda6")
    public static ModelComponent safeInstantiate(Component obj) throws IllegalArgumentException {
        if (ModelComponent.canInstantiate(obj))
        	return new ModelComponent(obj);
        else
        	throw new IllegalArgumentException("ModelComponent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c7f101c8-b922-416a-be9a-a79538b35010")
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
        ModelComponent other = (ModelComponent) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Component}. 
     * @return the Component represented by this proxy, never null.
     */
    @objid ("cee98523-db2d-418d-92cb-060cfde7526d")
    public Component getElement() {
        return this.elt;
    }

    @objid ("71b4eed3-8285-4837-b17a-de1db8224302")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("514972c8-b27f-43f4-86f3-b70132f0dd4b")
    protected ModelComponent(Component elt) {
        this.elt = elt;
    }

    @objid ("9685b84a-47f2-4a11-b7a6-d392fda9c49b")
    public static final class MdaTypes {
        @objid ("d6e1c153-5c42-4bb5-ad3a-eef54be5ba5e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f856cf95-1eff-40de-8744-7c5f75bbce83")
        private static Stereotype MDAASSOCDEP;

        @objid ("0b3a0ed1-b8ae-4bff-8163-4f5697ca7855")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("421435e5-bda6-4343-82e4-d0fc8bf648fd")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00d0052c-0000-0143-0000-000000000000");
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
