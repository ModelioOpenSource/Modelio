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
 * Proxy class to handle a {@link OpaqueAction} with << UML2AddStructuralFeatureValueAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("24df620c-84f5-4efe-a9d0-98c1fa6cf86f")
public class UML2AddStructuralFeatureValueAction {
    @objid ("60e284a1-0189-4ad5-b88b-9a24d913cf6d")
    public static final String STEREOTYPE_NAME = "UML2AddStructuralFeatureValueAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("dc9cb6cc-727f-4fed-bee4-88b9ebbd6aef")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2AddStructuralFeatureValueAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("bd3da8e6-484b-4660-8aad-bc43eed6e191")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2AddStructuralFeatureValueAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >> then instantiate a {@link UML2AddStructuralFeatureValueAction} proxy.
     * 
     * @return a {@link UML2AddStructuralFeatureValueAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("9abbe52a-a1e9-45c4-afa0-05361e0c0b27")
    public static UML2AddStructuralFeatureValueAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2AddStructuralFeatureValueAction.STEREOTYPE_NAME);
        return UML2AddStructuralFeatureValueAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2AddStructuralFeatureValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2AddStructuralFeatureValueAction} proxy or <i>null</i>.
     */
    @objid ("33bd5359-adba-4f88-8536-5b5a8b60dd79")
    public static UML2AddStructuralFeatureValueAction instantiate(OpaqueAction obj) {
        return UML2AddStructuralFeatureValueAction.canInstantiate(obj) ? new UML2AddStructuralFeatureValueAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2AddStructuralFeatureValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2AddStructuralFeatureValueAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0a82c9fd-0cd4-4c3f-800c-c2713c05da17")
    public static UML2AddStructuralFeatureValueAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2AddStructuralFeatureValueAction.canInstantiate(obj))
        	return new UML2AddStructuralFeatureValueAction(obj);
        else
        	throw new IllegalArgumentException("UML2AddStructuralFeatureValueAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ee100f24-16c6-43e2-89ff-ab5ff74b734d")
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
        UML2AddStructuralFeatureValueAction other = (UML2AddStructuralFeatureValueAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("2fa595ce-f85d-4b68-93aa-5fea8f0b776d")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("88546711-e391-4ab4-9690-dd78e920cd90")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5696dfe5-f964-4c39-8acf-272525e1ce87")
    protected UML2AddStructuralFeatureValueAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("24fa7251-4d39-4e6e-9612-9484b246a75b")
    public static final class MdaTypes {
        @objid ("9a47366b-e4e9-4a85-9fe2-b81aa913a2e1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("75b42d4a-89d0-4b6a-a8f6-28b02b7be895")
        private static Stereotype MDAASSOCDEP;

        @objid ("32dcf538-9bc1-4dd4-ac4b-5b5a7179ce00")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("07822afd-f196-469a-b0af-e69e3acf1018")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "994fc1e3-c2f9-11de-8ac8-001302895b2b");
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
