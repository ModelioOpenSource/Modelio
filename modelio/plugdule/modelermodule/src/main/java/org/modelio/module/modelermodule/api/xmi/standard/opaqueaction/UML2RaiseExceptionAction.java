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
 * Proxy class to handle a {@link OpaqueAction} with << UML2RaiseExceptionAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("59fa1fa5-bbf1-4055-b693-6d921c22cd94")
public class UML2RaiseExceptionAction {
    @objid ("4216554c-eaf9-4e2f-950e-ae2297eb5219")
    public static final String STEREOTYPE_NAME = "UML2RaiseExceptionAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("2240e081-4c9f-4f3b-b3c7-c19f41f85c8f")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2RaiseExceptionAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2RaiseExceptionAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0be81269-5af2-4290-9ae4-afb176a34403")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RaiseExceptionAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2RaiseExceptionAction >> then instantiate a {@link UML2RaiseExceptionAction} proxy.
     * 
     * @return a {@link UML2RaiseExceptionAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("718c21d5-5cf8-42a3-ab18-63286cdb01d8")
    public static UML2RaiseExceptionAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RaiseExceptionAction.STEREOTYPE_NAME);
        return UML2RaiseExceptionAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2RaiseExceptionAction} proxy from a {@link OpaqueAction} stereotyped << UML2RaiseExceptionAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2RaiseExceptionAction} proxy or <i>null</i>.
     */
    @objid ("006d952c-3bf7-4f51-8942-e6ce9b1f1237")
    public static UML2RaiseExceptionAction instantiate(OpaqueAction obj) {
        return UML2RaiseExceptionAction.canInstantiate(obj) ? new UML2RaiseExceptionAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RaiseExceptionAction} proxy from a {@link OpaqueAction} stereotyped << UML2RaiseExceptionAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2RaiseExceptionAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9b559e7f-05af-415a-be2f-31c7c5f69be0")
    public static UML2RaiseExceptionAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2RaiseExceptionAction.canInstantiate(obj))
        	return new UML2RaiseExceptionAction(obj);
        else
        	throw new IllegalArgumentException("UML2RaiseExceptionAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("fc04924d-3c86-45c6-8c9f-b4edb6eb9422")
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
        UML2RaiseExceptionAction other = (UML2RaiseExceptionAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("703422f0-17ed-493f-8bba-164df1478a2d")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("a79b90a5-7bce-4b0e-8a92-44085a41c753")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8e324d5a-48ee-4524-bd95-022a195f6c6d")
    protected UML2RaiseExceptionAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("756bcc2b-8b40-459d-9679-09445f1d90d8")
    public static final class MdaTypes {
        @objid ("0399f155-00c5-4c77-bf2b-286018ea31c5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1e294edc-479e-4c49-b9d4-7661004c6c1b")
        private static Stereotype MDAASSOCDEP;

        @objid ("22afeca9-3d7d-47ca-b0e9-e7afed10e899")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5c225cca-2f47-4dfe-a20f-e3c55bb487bc")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "d09356f1-c2fc-11de-8ac8-001302895b2b");
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
