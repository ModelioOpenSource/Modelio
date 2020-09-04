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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReplyAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1072f154-5793-424b-9dbf-2eaa07f6a142")
public class UML2ReplyAction {
    @objid ("b084d554-98bf-42b0-b04b-738115685014")
    public static final String STEREOTYPE_NAME = "UML2ReplyAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("1e6b1056-5e68-438c-aef7-b91dfc8b7f46")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReplyAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReplyAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1cc6c477-79c8-4b05-b12f-f1182bfcd34f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReplyAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReplyAction >> then instantiate a {@link UML2ReplyAction} proxy.
     * 
     * @return a {@link UML2ReplyAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("519c6c9e-f80d-4eef-93ab-ed4218899bff")
    public static UML2ReplyAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReplyAction.STEREOTYPE_NAME);
        return UML2ReplyAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReplyAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReplyAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReplyAction} proxy or <i>null</i>.
     */
    @objid ("c7865535-a445-44fb-8ddd-77d3bb9bcbc1")
    public static UML2ReplyAction instantiate(OpaqueAction obj) {
        return UML2ReplyAction.canInstantiate(obj) ? new UML2ReplyAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReplyAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReplyAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReplyAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d9ffb48f-ec59-4181-b4d9-e53e8a3450a0")
    public static UML2ReplyAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReplyAction.canInstantiate(obj))
        	return new UML2ReplyAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReplyAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b1ec9bd0-dfc2-408d-868a-c7245d7598a2")
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
        UML2ReplyAction other = (UML2ReplyAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("8db8814c-0262-4241-812c-922042c2b7f1")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c5133845-1332-4902-8db7-078059848f5d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ed4e753a-5c6e-4640-8edf-3d1b66a5271d")
    protected UML2ReplyAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("e3df1d05-1797-48da-8ef8-fa4d80b22d66")
    public static final class MdaTypes {
        @objid ("79237385-5112-4281-ba40-f715e311bf4b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b08b6c08-d843-41e7-9b22-11503902c5d4")
        private static Stereotype MDAASSOCDEP;

        @objid ("3668b098-91de-484c-9f4f-629cb774c501")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e4649431-ef48-4724-9e68-3c8002e4c0ee")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c6a579b6-5d0d-11df-a996-001302895b2b");
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
