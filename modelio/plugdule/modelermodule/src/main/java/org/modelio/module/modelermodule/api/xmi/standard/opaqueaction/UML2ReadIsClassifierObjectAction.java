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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadIsClassifierObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("2863745e-922c-4eff-ae51-cb047027a4c0")
public class UML2ReadIsClassifierObjectAction {
    @objid ("3af8f32a-176f-4f67-97f2-e76a18958c20")
    public static final String STEREOTYPE_NAME = "UML2ReadIsClassifierObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("08d6a1bf-bee1-4378-857d-ab3db73f8ed9")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadIsClassifierObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("4c678b20-34f9-4a5b-b701-7d71eba362af")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadIsClassifierObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >> then instantiate a {@link UML2ReadIsClassifierObjectAction} proxy.
     * 
     * @return a {@link UML2ReadIsClassifierObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("b948b4aa-aaae-4086-b669-2799aba3183b")
    public static UML2ReadIsClassifierObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadIsClassifierObjectAction.STEREOTYPE_NAME);
        return UML2ReadIsClassifierObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadIsClassifierObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadIsClassifierObjectAction} proxy or <i>null</i>.
     */
    @objid ("c1825242-5700-491b-9b46-3f17444347d5")
    public static UML2ReadIsClassifierObjectAction instantiate(OpaqueAction obj) {
        return UML2ReadIsClassifierObjectAction.canInstantiate(obj) ? new UML2ReadIsClassifierObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadIsClassifierObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadIsClassifierObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ccf7b1e1-b8d6-4bfc-b6d0-afddc8a116ef")
    public static UML2ReadIsClassifierObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadIsClassifierObjectAction.canInstantiate(obj))
        	return new UML2ReadIsClassifierObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadIsClassifierObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ba515ed4-9e84-49a6-a420-21164290ce93")
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
        UML2ReadIsClassifierObjectAction other = (UML2ReadIsClassifierObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("e98dbe02-6ac8-41ae-953c-e1281ced5ca2")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("ce2793d3-bdc7-46be-96c5-f0e9c7d45e0a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6dafbdbe-2351-419b-a4c7-93f327073b94")
    protected UML2ReadIsClassifierObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("56ed5dde-97b3-43a7-80e9-7b0b898107d8")
    public static final class MdaTypes {
        @objid ("573dd64a-2d8d-4f3a-9059-ae6e1fded2a7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a21cb828-fd36-4215-aa2e-2a420f9a2767")
        private static Stereotype MDAASSOCDEP;

        @objid ("a9bf938e-e618-4d1f-af05-2155b7b55c6e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e6075e32-33f1-40a7-bff8-5b892d5d0841")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e4c6c55f-c2fc-11de-8ac8-001302895b2b");
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
