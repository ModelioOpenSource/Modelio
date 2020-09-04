/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.opaqueaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2RemoveStructuralFeatureAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("483e6c61-31d1-4ff3-912d-56394bd2ed10")
public class UML2RemoveStructuralFeatureAction {
    @objid ("e3e5eebc-1985-4d79-b926-222ac59d5f4d")
    public static final String STEREOTYPE_NAME = "UML2RemoveStructuralFeatureAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("fed1a7d8-4a34-4bae-923f-a094fac51c74")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2RemoveStructuralFeatureAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("a6e5e742-e759-43cd-88b5-5d06f3600d46")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RemoveStructuralFeatureAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >> then instantiate a {@link UML2RemoveStructuralFeatureAction} proxy.
     * 
     * @return a {@link UML2RemoveStructuralFeatureAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("36fe7c81-3f2b-422d-b576-85a3c6e75883")
    public static UML2RemoveStructuralFeatureAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RemoveStructuralFeatureAction.STEREOTYPE_NAME);
        return UML2RemoveStructuralFeatureAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2RemoveStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2RemoveStructuralFeatureAction} proxy or <i>null</i>.
     */
    @objid ("5e0ada36-7d69-449e-b22b-a37dff5e4429")
    public static UML2RemoveStructuralFeatureAction instantiate(OpaqueAction obj) {
        return UML2RemoveStructuralFeatureAction.canInstantiate(obj) ? new UML2RemoveStructuralFeatureAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RemoveStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2RemoveStructuralFeatureAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("123132bd-9416-4f42-9d1f-66435605f752")
    public static UML2RemoveStructuralFeatureAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2RemoveStructuralFeatureAction.canInstantiate(obj))
        	return new UML2RemoveStructuralFeatureAction(obj);
        else
        	throw new IllegalArgumentException("UML2RemoveStructuralFeatureAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("15a7c526-3262-4e23-bbb1-7a581da753f4")
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
        UML2RemoveStructuralFeatureAction other = (UML2RemoveStructuralFeatureAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("0966d2ac-f5e8-4f1f-a99f-0f0c9b13f262")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("70d22104-acf5-451c-842e-c0ac4e96703e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("1a33f821-1efa-4199-80c1-79bcb1112456")
    protected UML2RemoveStructuralFeatureAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("5059f30e-33d1-46b2-a966-8c981d0db622")
    public static final class MdaTypes {
        @objid ("b2b0fe71-b5df-4909-9da3-33786492ae06")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ff43ec66-0924-453a-a049-bd3b81ddc791")
        private static Stereotype MDAASSOCDEP;

        @objid ("c7f09608-fe10-47a4-bda8-b7b40c4c07be")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b67be4c1-db36-4d72-8418-b0d024c5ff74")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "35b84299-c2fd-11de-8ac8-001302895b2b");
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
