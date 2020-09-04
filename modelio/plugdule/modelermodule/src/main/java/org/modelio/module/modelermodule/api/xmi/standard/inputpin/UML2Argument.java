/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
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
 * Proxy class to handle a {@link InputPin} with << UML2Argument >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f227bc75-d200-4586-8356-abed568e953a")
public class UML2Argument {
    @objid ("7492dbfc-5eda-401a-85a9-1f365a7fb5a9")
    public static final String STEREOTYPE_NAME = "UML2Argument";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("7aa68e55-7588-4824-b0b8-3dbb76c0f1b2")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Argument proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Argument >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d5b7e905-07c1-4f96-a852-b06ccf3b23a8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Argument.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Argument >> then instantiate a {@link UML2Argument} proxy.
     * 
     * @return a {@link UML2Argument} proxy on the created {@link InputPin}.
     */
    @objid ("51f21f90-f9d4-4067-afa6-e50d2f3f0c18")
    public static UML2Argument create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Argument.STEREOTYPE_NAME);
        return UML2Argument.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Argument} proxy from a {@link InputPin} stereotyped << UML2Argument >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Argument} proxy or <i>null</i>.
     */
    @objid ("fc89a328-e1d3-4601-8dcf-6cefeb109d61")
    public static UML2Argument instantiate(InputPin obj) {
        return UML2Argument.canInstantiate(obj) ? new UML2Argument(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Argument} proxy from a {@link InputPin} stereotyped << UML2Argument >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Argument} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("400b96ac-686d-4990-9b8f-7545ca9f0b1f")
    public static UML2Argument safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Argument.canInstantiate(obj))
        	return new UML2Argument(obj);
        else
        	throw new IllegalArgumentException("UML2Argument: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c8cadbe3-57c5-4362-9f60-9cea32b1c81c")
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
        UML2Argument other = (UML2Argument) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("1752c7a5-d902-425b-ba43-a53a8e7eae2b")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("b93c43e8-aefe-4bd7-9e7a-199cd4c544b1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("57967502-a4f4-4fbb-9061-2f7d3a2745f5")
    protected UML2Argument(InputPin elt) {
        this.elt = elt;
    }

    @objid ("35371a23-7a89-4c20-ae40-4ea2e5ecc371")
    public static final class MdaTypes {
        @objid ("f9b2830d-4075-4d05-a679-c953e1e7ca5a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0a624545-9662-4d0e-ac10-3886525cc99e")
        private static Stereotype MDAASSOCDEP;

        @objid ("7e59c419-4a3a-406a-8ef4-78b182594833")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a70cc359-e6a0-45b8-af5b-f29c974b8a0a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "86630901-a400-4353-8b38-6db0846d1e38");
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
