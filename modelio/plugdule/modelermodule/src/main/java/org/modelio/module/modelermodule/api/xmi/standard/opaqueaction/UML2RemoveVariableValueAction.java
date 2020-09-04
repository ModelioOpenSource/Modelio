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
 * Proxy class to handle a {@link OpaqueAction} with << UML2RemoveVariableValueAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d8be3f15-859f-4f75-bcf6-706f60bc54c9")
public class UML2RemoveVariableValueAction {
    @objid ("7a51de83-b8b0-494a-b080-08792758e2c8")
    public static final String STEREOTYPE_NAME = "UML2RemoveVariableValueAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("44e432e8-7b25-4808-b2a7-0ba95a03a9f5")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2RemoveVariableValueAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("ab7b75bb-d07f-4e4f-8f85-4780d3794135")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RemoveVariableValueAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >> then instantiate a {@link UML2RemoveVariableValueAction} proxy.
     * 
     * @return a {@link UML2RemoveVariableValueAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("c2b420ce-ec67-4720-82b7-b4baabd77832")
    public static UML2RemoveVariableValueAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RemoveVariableValueAction.STEREOTYPE_NAME);
        return UML2RemoveVariableValueAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2RemoveVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2RemoveVariableValueAction} proxy or <i>null</i>.
     */
    @objid ("d98053e4-4bd8-43ff-9a0a-74cc6e36028c")
    public static UML2RemoveVariableValueAction instantiate(OpaqueAction obj) {
        return UML2RemoveVariableValueAction.canInstantiate(obj) ? new UML2RemoveVariableValueAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RemoveVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2RemoveVariableValueAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c9d9a117-7daf-4665-9c30-035081b67dde")
    public static UML2RemoveVariableValueAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2RemoveVariableValueAction.canInstantiate(obj))
        	return new UML2RemoveVariableValueAction(obj);
        else
        	throw new IllegalArgumentException("UML2RemoveVariableValueAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ee99a4d9-31b9-433c-b32d-c4ca4f12acae")
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
        UML2RemoveVariableValueAction other = (UML2RemoveVariableValueAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("b7372e25-3f46-48b9-9421-7e10a3eb8ce3")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("7cfa3e33-b7c1-4ec8-bb67-a171d1741d42")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("403c4f7d-fa75-428f-98b3-97676b552c67")
    protected UML2RemoveVariableValueAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("4788dd43-ab73-4535-a12f-facdd8ad949a")
    public static final class MdaTypes {
        @objid ("0b0293b2-eda5-4775-ae78-1a50a5aa26a5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("59abe801-cff1-40b9-8aa6-3b0a4815d3e7")
        private static Stereotype MDAASSOCDEP;

        @objid ("91829209-0ec2-4811-84ad-c47922b3d69d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9e9afbdc-3370-4464-85ad-4fba0106845c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "435869cb-c2fd-11de-8ac8-001302895b2b");
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
