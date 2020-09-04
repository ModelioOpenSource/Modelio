/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << destroy >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("699eb4cb-af8a-484f-af24-91a599fada81")
public class Destroy {
    @objid ("0d59c539-f171-4110-8ed3-f58beaa2eec1")
    public static final String STEREOTYPE_NAME = "destroy";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("4261235d-ae51-4423-a075-4464f01795c6")
    protected final Operation elt;

    /**
     * Tells whether a {@link Destroy proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << destroy >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("5fb14b75-c4c0-4640-8b6e-a21adbe9fa3c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Destroy.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << destroy >> then instantiate a {@link Destroy} proxy.
     * 
     * @return a {@link Destroy} proxy on the created {@link Operation}.
     */
    @objid ("cfb684e5-da52-41c7-9b74-4da368363103")
    public static Destroy create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Destroy.STEREOTYPE_NAME);
        return Destroy.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link Destroy} proxy from a {@link Operation} stereotyped << destroy >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link Destroy} proxy or <i>null</i>.
     */
    @objid ("ce10a0e4-30c7-40fb-af93-4f5b30c9ee64")
    public static Destroy instantiate(Operation obj) {
        return Destroy.canInstantiate(obj) ? new Destroy(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Destroy} proxy from a {@link Operation} stereotyped << destroy >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link Destroy} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("16cd9b3b-aa61-4955-a580-c217170348dd")
    public static Destroy safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (Destroy.canInstantiate(obj))
        	return new Destroy(obj);
        else
        	throw new IllegalArgumentException("Destroy: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cec9f7fd-794e-480f-928e-11060f9d8b4d")
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
        Destroy other = (Destroy) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("50e3f872-e9b7-4e25-b355-3d980e1e47d3")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("74164f4f-cc78-41e4-8140-74bc50607f03")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("93c14916-0078-47de-b8c7-f220ec31123d")
    protected Destroy(Operation elt) {
        this.elt = elt;
    }

    @objid ("452f4dff-b119-47dc-9dd6-c88f4d500896")
    public static final class MdaTypes {
        @objid ("39625b1f-d2bf-43a4-9179-a8a47a66746e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a3afdd28-06b3-470f-bde2-818b5995c183")
        private static Stereotype MDAASSOCDEP;

        @objid ("456e7fe5-90ab-4a6e-b3b5-5f37567eefee")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("25994f8f-272d-4c0d-8946-9d37889969a0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-0206-0000-000000000000");
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
