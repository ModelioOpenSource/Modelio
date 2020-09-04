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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ClearStructuralFeatureAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("53b7b102-f9af-40b7-8249-2817a64d671f")
public class UML2ClearStructuralFeatureAction {
    @objid ("7688548f-1c2a-4688-9867-826bdabfdb2f")
    public static final String STEREOTYPE_NAME = "UML2ClearStructuralFeatureAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("8dbdec93-4843-42f7-be47-7d96bcb9ab8b")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ClearStructuralFeatureAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3708b77a-4819-4e8b-9b5d-7875b285a2fc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClearStructuralFeatureAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >> then instantiate a {@link UML2ClearStructuralFeatureAction} proxy.
     * 
     * @return a {@link UML2ClearStructuralFeatureAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("a36f76e4-e01a-43e1-b8f1-df3b1e122d43")
    public static UML2ClearStructuralFeatureAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClearStructuralFeatureAction.STEREOTYPE_NAME);
        return UML2ClearStructuralFeatureAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClearStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ClearStructuralFeatureAction} proxy or <i>null</i>.
     */
    @objid ("595e42d7-000c-4e49-8de8-95c5b44cdee1")
    public static UML2ClearStructuralFeatureAction instantiate(OpaqueAction obj) {
        return UML2ClearStructuralFeatureAction.canInstantiate(obj) ? new UML2ClearStructuralFeatureAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClearStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ClearStructuralFeatureAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1eeab0c6-2c35-473e-864a-84dead6a8bd2")
    public static UML2ClearStructuralFeatureAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ClearStructuralFeatureAction.canInstantiate(obj))
        	return new UML2ClearStructuralFeatureAction(obj);
        else
        	throw new IllegalArgumentException("UML2ClearStructuralFeatureAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0775de16-17d6-407b-9887-18d9f7b79b66")
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
        UML2ClearStructuralFeatureAction other = (UML2ClearStructuralFeatureAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("06d238c2-11b3-4354-80cc-8dccffd94250")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c636cf54-c772-4e93-a7d9-6e660cfe3679")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0851e0fb-d00e-46f1-b503-ffa93aca46c0")
    protected UML2ClearStructuralFeatureAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("572079e7-f6d0-490a-884b-150e1d9022cb")
    public static final class MdaTypes {
        @objid ("a65f84fa-2410-4e01-a11d-9826ab400a2d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c142234b-ad6e-4461-9dc1-0fa3e56e1ae0")
        private static Stereotype MDAASSOCDEP;

        @objid ("d8107214-a93a-466b-9111-f3f0ba81494e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4ffa358a-f9fb-4efb-8b51-a27dc5ac5a0b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "9fb5321d-c2fc-11de-8ac8-001302895b2b");
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
