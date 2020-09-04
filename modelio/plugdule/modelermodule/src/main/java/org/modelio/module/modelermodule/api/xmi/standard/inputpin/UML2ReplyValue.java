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
 * Proxy class to handle a {@link InputPin} with << UML2ReplyValue >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c4735e0b-dee0-4706-908a-49b4f1702ab4")
public class UML2ReplyValue {
    @objid ("19ae8b34-1c7c-4669-80b7-8bd6456d6d80")
    public static final String STEREOTYPE_NAME = "UML2ReplyValue";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("7eedcf13-c5e8-4637-aad2-172ec58d198b")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2ReplyValue proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2ReplyValue >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("08fef8fb-3722-4dea-816b-4ed09f2f8602")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReplyValue.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2ReplyValue >> then instantiate a {@link UML2ReplyValue} proxy.
     * 
     * @return a {@link UML2ReplyValue} proxy on the created {@link InputPin}.
     */
    @objid ("582799bb-9bbb-4fe5-939f-3f7b4a5b6188")
    public static UML2ReplyValue create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReplyValue.STEREOTYPE_NAME);
        return UML2ReplyValue.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReplyValue} proxy from a {@link InputPin} stereotyped << UML2ReplyValue >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2ReplyValue} proxy or <i>null</i>.
     */
    @objid ("c43f67e7-446b-4cf0-844a-55edf33128fc")
    public static UML2ReplyValue instantiate(InputPin obj) {
        return UML2ReplyValue.canInstantiate(obj) ? new UML2ReplyValue(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReplyValue} proxy from a {@link InputPin} stereotyped << UML2ReplyValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2ReplyValue} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2adf851d-d36f-48b7-95b8-6f4fc8481802")
    public static UML2ReplyValue safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2ReplyValue.canInstantiate(obj))
        	return new UML2ReplyValue(obj);
        else
        	throw new IllegalArgumentException("UML2ReplyValue: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("47d98b4a-29c7-4c31-8534-1c541607fea3")
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
        UML2ReplyValue other = (UML2ReplyValue) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("fd330b07-c1e4-4c7b-8e05-6d5d51a85fa3")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("f25a7989-1e13-4e41-807e-78f0b2a703b3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c2e0eab4-46cb-47cb-9962-775d6f8c6569")
    protected UML2ReplyValue(InputPin elt) {
        this.elt = elt;
    }

    @objid ("9cd261d9-6c29-4e32-b584-35f043493a76")
    public static final class MdaTypes {
        @objid ("eac562e2-38c4-4b2d-91ba-e45c2331797c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4bcbe8dd-6368-41c1-98f7-8cd4b29958a8")
        private static Stereotype MDAASSOCDEP;

        @objid ("ef6b2b8b-9f29-476a-8ed2-410a6f14bec1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("65c1920f-2d61-42b3-9119-31822580f496")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "bdfb2aae-89a6-49b6-9a0d-3a5e4519cb31");
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
