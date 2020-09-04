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
 * Proxy class to handle a {@link Dependency} with << UML2ProtocolConformance >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fd03066d-0a51-4fb7-9404-e4fdea747c28")
public class UML2ProtocolConformance {
    @objid ("c29a15bf-4107-4347-87b7-7cedaf0882e2")
    public static final String STEREOTYPE_NAME = "UML2ProtocolConformance";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("647ea956-58d1-4d6b-b754-b36dc9431ba5")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ProtocolConformance proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ProtocolConformance >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f31e7489-5c5e-44f8-a7d8-083dd724a33a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ProtocolConformance.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ProtocolConformance >> then instantiate a {@link UML2ProtocolConformance} proxy.
     * 
     * @return a {@link UML2ProtocolConformance} proxy on the created {@link Dependency}.
     */
    @objid ("660e1f4c-2a71-4953-a71e-af0ff4523600")
    public static UML2ProtocolConformance create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ProtocolConformance.STEREOTYPE_NAME);
        return UML2ProtocolConformance.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ProtocolConformance} proxy from a {@link Dependency} stereotyped << UML2ProtocolConformance >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ProtocolConformance} proxy or <i>null</i>.
     */
    @objid ("837217bb-06b0-4c04-bf84-9672589d95e9")
    public static UML2ProtocolConformance instantiate(Dependency obj) {
        return UML2ProtocolConformance.canInstantiate(obj) ? new UML2ProtocolConformance(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ProtocolConformance} proxy from a {@link Dependency} stereotyped << UML2ProtocolConformance >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2ProtocolConformance} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("517ea075-3436-4dea-9829-ecf6a13f8bbc")
    public static UML2ProtocolConformance safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ProtocolConformance.canInstantiate(obj))
        	return new UML2ProtocolConformance(obj);
        else
        	throw new IllegalArgumentException("UML2ProtocolConformance: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7b5247ee-74b1-43fc-9fec-b1efc6743101")
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
        UML2ProtocolConformance other = (UML2ProtocolConformance) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("c6bbe2f3-4ad8-478c-809f-a2ceeafed612")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("37a9fba5-2388-484e-ac25-6030d3984343")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("34c91a4e-8535-42fb-882f-e9711977194c")
    protected UML2ProtocolConformance(Dependency elt) {
        this.elt = elt;
    }

    @objid ("04468c8e-8b2c-41f7-86f3-27c6af364806")
    public static final class MdaTypes {
        @objid ("e17623a6-7349-481b-940f-b031adc9b0be")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0ded1add-b552-4fdd-aaff-598d6d1bae08")
        private static Stereotype MDAASSOCDEP;

        @objid ("47f7c4d3-f19d-4d10-9867-5964532ab315")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5a1cfe15-e431-4f9f-a40b-a4375d381098")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3edfb381-5d0d-11df-a996-001302895b2b");
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
