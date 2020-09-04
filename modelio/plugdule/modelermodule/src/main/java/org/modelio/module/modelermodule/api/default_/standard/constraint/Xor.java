/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
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
 * Proxy class to handle a {@link Constraint} with << xor >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("068acec6-4e04-4c0c-b2a6-986644ad0778")
public class Xor {
    @objid ("63d97161-3db4-471f-83b4-e10be4dc30ef")
    public static final String STEREOTYPE_NAME = "xor";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("6d26eda7-a58d-4cfb-8d24-903e0b808f32")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Xor proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << xor >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("981048f2-a76d-4d50-8be8-f4fa3299416d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Xor.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << xor >> then instantiate a {@link Xor} proxy.
     * 
     * @return a {@link Xor} proxy on the created {@link Constraint}.
     */
    @objid ("d07e9e84-e325-4671-9690-5bbc9d16eb04")
    public static Xor create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Xor.STEREOTYPE_NAME);
        return Xor.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Xor} proxy from a {@link Constraint} stereotyped << xor >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Xor} proxy or <i>null</i>.
     */
    @objid ("2fe3e837-456d-4e48-a12a-d16db43d6765")
    public static Xor instantiate(Constraint obj) {
        return Xor.canInstantiate(obj) ? new Xor(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Xor} proxy from a {@link Constraint} stereotyped << xor >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Xor} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("194a6dfe-555e-4d43-9c90-21e46dafcb49")
    public static Xor safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Xor.canInstantiate(obj))
        	return new Xor(obj);
        else
        	throw new IllegalArgumentException("Xor: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6acb14ee-c84e-4495-b6ec-92eed6623532")
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
        Xor other = (Xor) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("8e896f72-0213-45b9-a2ac-9adb42c49330")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("57342735-be81-4e29-ad8f-5c490f402852")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b2e80659-70b8-427a-bf9c-0f9463116797")
    protected Xor(Constraint elt) {
        this.elt = elt;
    }

    @objid ("883eb518-ea9f-4c9d-a048-324ca600d764")
    public static final class MdaTypes {
        @objid ("a60e7ab9-53e8-41ca-87db-09eedb1c23b3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("66c60920-d244-4edc-9994-74c5e0339a69")
        private static Stereotype MDAASSOCDEP;

        @objid ("d37ec74f-2836-4bc0-a821-e061f522034e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5a418e35-5396-428d-acf0-bca0f6e27bff")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-0200-0000-000000000000");
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
