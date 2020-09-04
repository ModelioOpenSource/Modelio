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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadExtentAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f44efdd0-3ec1-476f-899a-8869358862ec")
public class UML2ReadExtentAction {
    @objid ("dc98ee3e-47a3-4c49-bdf5-3fef2b498059")
    public static final String STEREOTYPE_NAME = "UML2ReadExtentAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("406207a3-9fc4-4ec9-bc6a-5240025ec6b3")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadExtentAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadExtentAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1b5edefd-89a3-42c6-b9d0-301f335139ba")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadExtentAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadExtentAction >> then instantiate a {@link UML2ReadExtentAction} proxy.
     * 
     * @return a {@link UML2ReadExtentAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("6508425b-f1cc-40e8-bf77-33c6ef6340a6")
    public static UML2ReadExtentAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadExtentAction.STEREOTYPE_NAME);
        return UML2ReadExtentAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadExtentAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadExtentAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadExtentAction} proxy or <i>null</i>.
     */
    @objid ("b2843da3-e1d5-4e2a-b4a8-13fb9c0104bc")
    public static UML2ReadExtentAction instantiate(OpaqueAction obj) {
        return UML2ReadExtentAction.canInstantiate(obj) ? new UML2ReadExtentAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadExtentAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadExtentAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadExtentAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("dadbf6d1-0db8-4a51-84e0-8fb592b19c0f")
    public static UML2ReadExtentAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadExtentAction.canInstantiate(obj))
        	return new UML2ReadExtentAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadExtentAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("01a692f7-548b-4b2b-9b5c-c90847bb3c7e")
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
        UML2ReadExtentAction other = (UML2ReadExtentAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("d8ad85e3-12f7-41e1-b78f-a29332dbeb63")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("09c8ede8-d9e0-47a1-b1e2-f385012361dc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a630dc91-1ef5-47df-b17e-0bb1be1e266c")
    protected UML2ReadExtentAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("5b0571b6-d970-4ab3-9d56-4b8aba8bc9d6")
    public static final class MdaTypes {
        @objid ("babbcf68-5e5a-48b1-90a6-9178dd625746")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4c67afd5-22a5-464c-a27f-6d1cdd9e8bed")
        private static Stereotype MDAASSOCDEP;

        @objid ("022f7789-d63c-4c9b-9f17-c83a7b222a40")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5640912f-70ef-4a23-a9a9-c315cb9c765a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c4b3add1-c2f9-11de-8ac8-001302895b2b");
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
