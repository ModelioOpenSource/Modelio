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
 * Proxy class to handle a {@link OpaqueAction} with << UML2DestroyObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ca7cc715-a18b-49dd-b91e-187dccae7ec1")
public class UML2DestroyObjectAction {
    @objid ("abb5b457-1d25-4ae6-9a0d-a265e94b32a9")
    public static final String STEREOTYPE_NAME = "UML2DestroyObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("d7dde7e5-3e8f-453a-88d7-10faac560ded")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2DestroyObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("fd29e333-5d67-412b-9875-cd98c99831ba")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DestroyObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >> then instantiate a {@link UML2DestroyObjectAction} proxy.
     * 
     * @return a {@link UML2DestroyObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("8e52cd9f-a533-413f-b062-8c9df7c3dcc0")
    public static UML2DestroyObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DestroyObjectAction.STEREOTYPE_NAME);
        return UML2DestroyObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2DestroyObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2DestroyObjectAction} proxy or <i>null</i>.
     */
    @objid ("049b41c9-e2b5-4f81-a542-28cb48c55ab6")
    public static UML2DestroyObjectAction instantiate(OpaqueAction obj) {
        return UML2DestroyObjectAction.canInstantiate(obj) ? new UML2DestroyObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2DestroyObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2DestroyObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2DestroyObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c6164120-ccb2-4143-bb3a-0f2744127fce")
    public static UML2DestroyObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2DestroyObjectAction.canInstantiate(obj))
        	return new UML2DestroyObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2DestroyObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("40ad4f9e-fb2d-4fc0-8a64-797791d181ba")
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
        UML2DestroyObjectAction other = (UML2DestroyObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("223191de-92dc-4995-8632-7aefaf1f8921")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("5b616ea6-3e48-4b0e-ba78-9e2466f1b958")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f8900e62-c424-4f2a-956c-348e059aa88e")
    protected UML2DestroyObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("0b742e7e-97c9-4b51-8f98-806bd4659614")
    public static final class MdaTypes {
        @objid ("5fee0be3-8b6e-4ad7-9fc4-e031b8cacddf")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("69d15f99-1404-4676-b99d-02c55294bb85")
        private static Stereotype MDAASSOCDEP;

        @objid ("f28bdec8-8fe9-4618-9b4e-c6a4acaf2f47")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1ee3d59a-8186-4e38-aa1b-44526a87e442")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "cf671bc3-c2f9-11de-8ac8-001302895b2b");
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
