/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.operation;

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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << UML2RedefinableTemplateSignature >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("63263b0d-3e0b-4d58-b78f-6a6ecff63f13")
public class UML2RedefinableTemplateSignature {
    @objid ("4752edab-60bf-4baf-b105-1b1127dbc9ff")
    public static final String STEREOTYPE_NAME = "UML2RedefinableTemplateSignature";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("c7beff8e-c8af-4ace-9d5a-3885a8b149f2")
    protected final Operation elt;

    /**
     * Tells whether a {@link UML2RedefinableTemplateSignature proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << UML2RedefinableTemplateSignature >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9e456b5a-24b3-45d2-aa1c-5c796e7b525c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RedefinableTemplateSignature.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << UML2RedefinableTemplateSignature >> then instantiate a {@link UML2RedefinableTemplateSignature} proxy.
     * 
     * @return a {@link UML2RedefinableTemplateSignature} proxy on the created {@link Operation}.
     */
    @objid ("fe34ef52-8885-4874-a308-cb5f8ce26516")
    public static UML2RedefinableTemplateSignature create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RedefinableTemplateSignature.STEREOTYPE_NAME);
        return UML2RedefinableTemplateSignature.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link UML2RedefinableTemplateSignature} proxy from a {@link Operation} stereotyped << UML2RedefinableTemplateSignature >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link UML2RedefinableTemplateSignature} proxy or <i>null</i>.
     */
    @objid ("e57c7429-367a-4dcd-8f4d-eb55e6002dd8")
    public static UML2RedefinableTemplateSignature instantiate(Operation obj) {
        return UML2RedefinableTemplateSignature.canInstantiate(obj) ? new UML2RedefinableTemplateSignature(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RedefinableTemplateSignature} proxy from a {@link Operation} stereotyped << UML2RedefinableTemplateSignature >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link UML2RedefinableTemplateSignature} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("65e5e81f-87e5-43ca-a3e3-95a63b45b048")
    public static UML2RedefinableTemplateSignature safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (UML2RedefinableTemplateSignature.canInstantiate(obj))
        	return new UML2RedefinableTemplateSignature(obj);
        else
        	throw new IllegalArgumentException("UML2RedefinableTemplateSignature: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("54e135bc-faf4-430e-a5d0-bff9a23a36c3")
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
        UML2RedefinableTemplateSignature other = (UML2RedefinableTemplateSignature) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("83a3d0af-9bbd-484a-8f7a-8c0cb150d2c4")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("36ac3374-4e62-4ce8-9f56-0c30efd88ac2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2c3decf5-c1ba-4587-b7fc-0f7be37e818b")
    protected UML2RedefinableTemplateSignature(Operation elt) {
        this.elt = elt;
    }

    @objid ("17d7053e-244f-455d-aa7a-63194a9e59bb")
    public static final class MdaTypes {
        @objid ("b944930d-5c0e-42b5-a8a8-e6c655bf9bc0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("076ec508-b7eb-443b-8a13-36a5fb52766c")
        private static Stereotype MDAASSOCDEP;

        @objid ("77e34bdc-5250-4c7e-ab32-6bb2018cd09d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3ddadba7-9c75-463c-953b-09acb69c9b33")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "90a0e341-5d0d-11df-a996-001302895b2b");
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
