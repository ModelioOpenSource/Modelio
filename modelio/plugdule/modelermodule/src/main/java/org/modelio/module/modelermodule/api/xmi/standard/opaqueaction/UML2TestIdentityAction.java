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
 * Proxy class to handle a {@link OpaqueAction} with << UML2TestIdentityAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("4865d52c-c25c-4167-ada9-55cc79a6e9e3")
public class UML2TestIdentityAction {
    @objid ("0b8cbc5c-8ae7-4cee-9e43-50b4bcf9345e")
    public static final String STEREOTYPE_NAME = "UML2TestIdentityAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("14048cdf-0d17-4ad8-a12b-3e2e021b9819")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2TestIdentityAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2TestIdentityAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("cd08cdac-644f-4161-8963-235584444c94")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2TestIdentityAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2TestIdentityAction >> then instantiate a {@link UML2TestIdentityAction} proxy.
     * 
     * @return a {@link UML2TestIdentityAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("4a1dc5f0-4196-4944-b961-85f7ecc22783")
    public static UML2TestIdentityAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2TestIdentityAction.STEREOTYPE_NAME);
        return UML2TestIdentityAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2TestIdentityAction} proxy from a {@link OpaqueAction} stereotyped << UML2TestIdentityAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2TestIdentityAction} proxy or <i>null</i>.
     */
    @objid ("0bf14453-cad6-45e8-95ad-3c80e5d6ddd7")
    public static UML2TestIdentityAction instantiate(OpaqueAction obj) {
        return UML2TestIdentityAction.canInstantiate(obj) ? new UML2TestIdentityAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2TestIdentityAction} proxy from a {@link OpaqueAction} stereotyped << UML2TestIdentityAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2TestIdentityAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("051e3a51-f249-4547-a1d0-165fff8274f7")
    public static UML2TestIdentityAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2TestIdentityAction.canInstantiate(obj))
        	return new UML2TestIdentityAction(obj);
        else
        	throw new IllegalArgumentException("UML2TestIdentityAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6a861822-beb4-4b62-91d6-7ef602dbc3d0")
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
        UML2TestIdentityAction other = (UML2TestIdentityAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("3ee49f02-d8d2-49df-8ac8-ba3fda853ac6")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("ade8c8e4-82e4-4816-9345-ddb8a865a1e6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c67991d4-ebba-4a0f-b4b0-c7715b66aecf")
    protected UML2TestIdentityAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("203724f2-4489-44f6-9abf-f0908a8c4247")
    public static final class MdaTypes {
        @objid ("60c8f83c-2796-48a6-8c69-209e497e8a8a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7ad3b9a7-c5f0-4cc0-ba80-4df32be782a5")
        private static Stereotype MDAASSOCDEP;

        @objid ("c5520b72-024c-4536-a5f4-e641a1e08ea6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f1120de9-3481-4ea3-b2d1-787b61c9bee5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6a3f6989-c2fd-11de-8ac8-001302895b2b");
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
