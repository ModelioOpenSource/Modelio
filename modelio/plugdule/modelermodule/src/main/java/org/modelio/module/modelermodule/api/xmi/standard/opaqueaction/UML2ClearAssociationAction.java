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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ClearAssociationAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("288adbf4-c847-449e-8f5e-4defde131fe0")
public class UML2ClearAssociationAction {
    @objid ("cabeb05c-014b-4f52-b651-5f545769caf9")
    public static final String STEREOTYPE_NAME = "UML2ClearAssociationAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("06069351-264e-42cc-9a98-357f25838dd2")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ClearAssociationAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("32c16354-1da1-4519-90cd-700c8625a535")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClearAssociationAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >> then instantiate a {@link UML2ClearAssociationAction} proxy.
     * 
     * @return a {@link UML2ClearAssociationAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("6bfd71f5-ca16-4116-b7dc-6f5c069b297e")
    public static UML2ClearAssociationAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClearAssociationAction.STEREOTYPE_NAME);
        return UML2ClearAssociationAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClearAssociationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ClearAssociationAction} proxy or <i>null</i>.
     */
    @objid ("9da43f7a-0d39-4688-8cf2-bcfc2849fe70")
    public static UML2ClearAssociationAction instantiate(OpaqueAction obj) {
        return UML2ClearAssociationAction.canInstantiate(obj) ? new UML2ClearAssociationAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClearAssociationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ClearAssociationAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("62bd0efa-3388-43a0-af54-80d85b6340c3")
    public static UML2ClearAssociationAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ClearAssociationAction.canInstantiate(obj))
        	return new UML2ClearAssociationAction(obj);
        else
        	throw new IllegalArgumentException("UML2ClearAssociationAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("57d380ba-9484-4682-bc94-1107f405b68a")
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
        UML2ClearAssociationAction other = (UML2ClearAssociationAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("def5fbe9-94af-40f7-abd2-470330e7390b")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("83f230e4-f9d5-4ed3-a0a6-51ab61f4f2b9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7d720e89-9c82-4073-873c-230cbd58860f")
    protected UML2ClearAssociationAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("c62134b2-bb18-4381-9964-0e7216166c07")
    public static final class MdaTypes {
        @objid ("22a702d0-3f8b-48a4-9543-d14a878ac952")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7e3e32c7-4f9e-4d54-be50-85e42fcb27f1")
        private static Stereotype MDAASSOCDEP;

        @objid ("69fc1ca7-244a-42e3-8b48-546ad180bba7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("67b51260-0d66-4c49-bfc1-69d320057adc")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "89927bbf-c2f9-11de-8ac8-001302895b2b");
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
