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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadStructuralFeatureAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("2b0053d4-f916-41fb-ae12-2a439d6dce56")
public class UML2ReadStructuralFeatureAction {
    @objid ("7025aca2-5d69-448b-91b6-8990ab4bf298")
    public static final String STEREOTYPE_NAME = "UML2ReadStructuralFeatureAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("2cf3db57-9f1a-4c30-a96a-e7204234729b")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadStructuralFeatureAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("bcc1ece8-e557-4227-8794-eca51e01973f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadStructuralFeatureAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >> then instantiate a {@link UML2ReadStructuralFeatureAction} proxy.
     * 
     * @return a {@link UML2ReadStructuralFeatureAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("501d182f-80d7-49c2-9b5f-61a47ea1db18")
    public static UML2ReadStructuralFeatureAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadStructuralFeatureAction.STEREOTYPE_NAME);
        return UML2ReadStructuralFeatureAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadStructuralFeatureAction} proxy or <i>null</i>.
     */
    @objid ("82dd3a1a-4f76-4d43-9579-111899c96736")
    public static UML2ReadStructuralFeatureAction instantiate(OpaqueAction obj) {
        return UML2ReadStructuralFeatureAction.canInstantiate(obj) ? new UML2ReadStructuralFeatureAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadStructuralFeatureAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7dd58a46-5d1b-4b7e-b5c4-d5bf67bafec1")
    public static UML2ReadStructuralFeatureAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadStructuralFeatureAction.canInstantiate(obj))
        	return new UML2ReadStructuralFeatureAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadStructuralFeatureAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("069ecb0a-e719-4603-9e24-36144dc79624")
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
        UML2ReadStructuralFeatureAction other = (UML2ReadStructuralFeatureAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("39fbcb85-ac2e-4013-b8f7-cb7c13ff591d")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("cdaba0b8-36d0-42de-8486-ed4170680c99")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d3e8cb90-6e8a-423c-b7ee-a734dfb35099")
    protected UML2ReadStructuralFeatureAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("e8682448-1fad-44d6-88fd-6b31e1005623")
    public static final class MdaTypes {
        @objid ("680a188f-53be-4d2c-b2e5-8994b0192bf5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fff450d5-96b1-40b8-8da1-fad7ff6063d0")
        private static Stereotype MDAASSOCDEP;

        @objid ("82195e89-14e4-47f0-b90f-8399461b701f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("10a721e1-9a76-4cd1-8185-e905ae23d315")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b9654705-c2f9-11de-8ac8-001302895b2b");
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
