/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << UML2Abstraction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9d1e11d0-fed8-4531-a0ff-914ea423a867")
public class UML2Abstraction {
    @objid ("dec1799e-2e84-4a52-ac4f-97d81455daeb")
    public static final String STEREOTYPE_NAME = "UML2Abstraction";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("795e4ed3-af40-4e22-83a2-7a405fbea74c")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2Abstraction proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2Abstraction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("fbfe4625-2270-4365-9735-b01525439706")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Abstraction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2Abstraction >> then instantiate a {@link UML2Abstraction} proxy.
     * 
     * @return a {@link UML2Abstraction} proxy on the created {@link Dependency}.
     */
    @objid ("1bf48a01-b9a6-4572-b0c8-ebfc1e9132c6")
    public static UML2Abstraction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Abstraction.STEREOTYPE_NAME);
        return UML2Abstraction.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2Abstraction} proxy from a {@link Dependency} stereotyped << UML2Abstraction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2Abstraction} proxy or <i>null</i>.
     */
    @objid ("881938f4-1964-4edb-9b84-2c5ecc1ef5fd")
    public static UML2Abstraction instantiate(Dependency obj) {
        return UML2Abstraction.canInstantiate(obj) ? new UML2Abstraction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Abstraction} proxy from a {@link Dependency} stereotyped << UML2Abstraction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2Abstraction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e6a1de12-7a58-4722-b4e4-a2b5495cdcee")
    public static UML2Abstraction safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2Abstraction.canInstantiate(obj))
        	return new UML2Abstraction(obj);
        else
        	throw new IllegalArgumentException("UML2Abstraction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("73bde495-02fe-4922-89da-33008e895749")
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
        UML2Abstraction other = (UML2Abstraction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("1d3b3bdf-9393-4a9b-8b55-838439ec9736")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("0762b634-ed4d-4842-8253-ed552edab436")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d95f3afc-a318-49f2-bed2-9fafb678595c")
    protected UML2Abstraction(Dependency elt) {
        this.elt = elt;
    }

    @objid ("6d9d3b85-b56b-481e-8799-f55b8683359f")
    public static final class MdaTypes {
        @objid ("77ae58ba-d45c-4ef2-8aaf-036a2c2b6006")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("dc210efe-340e-4612-a905-958571a96959")
        private static Stereotype MDAASSOCDEP;

        @objid ("5e691c06-f020-408f-bbce-1ec19d880003")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c9e88d1d-5a8b-4311-963c-1a80114289fd")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b355cc6c-c4aa-11df-b100-001302895b2b");
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
