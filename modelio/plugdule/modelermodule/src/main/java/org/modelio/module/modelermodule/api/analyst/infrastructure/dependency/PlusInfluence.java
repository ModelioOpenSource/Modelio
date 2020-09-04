/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << +influence >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("42cd298d-9d7d-4787-9645-c8747b86583f")
public class PlusInfluence {
    @objid ("ac42efcf-e45d-4292-838a-befd863d0fab")
    public static final String STEREOTYPE_NAME = "+influence";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("41f024f8-f154-463e-bd2b-88dafd4476d9")
    protected final Dependency elt;

    /**
     * Tells whether a {@link PlusInfluence proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << +influence >>.
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f4b26c58-0577-4c52-bc5c-af96ce7e7327")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PlusInfluence.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << +influence >> then instantiate a {@link PlusInfluence} proxy.
     * @return a {@link PlusInfluence} proxy on the created {@link Dependency}.
     */
    @objid ("091a858e-0182-4867-b6f1-a380dfec5fd5")
    public static PlusInfluence create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PlusInfluence.STEREOTYPE_NAME);
        return PlusInfluence.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link PlusInfluence} proxy from a {@link Dependency} stereotyped << +influence >>checking its metaclass and its stereotype.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link PlusInfluence} proxy or <i>null</i>.
     */
    @objid ("9f511a05-30e7-4dd2-8f0e-15bd0ef5e93c")
    public static PlusInfluence instantiate(Dependency obj) {
        return PlusInfluence.canInstantiate(obj) ? new PlusInfluence(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PlusInfluence} proxy from a {@link Dependency} stereotyped << +influence >> checking its metaclass and its stereotype.
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link PlusInfluence} proxy.
     * @throws java.lang.IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f28faa4b-4c79-43ec-b9b7-773c6761ded5")
    public static PlusInfluence safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (PlusInfluence.canInstantiate(obj))
            return new PlusInfluence(obj);
        else
            throw new IllegalArgumentException("PlusInfluence: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("679247d4-28b9-4ada-ac5f-5aec5ea54b66")
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
        PlusInfluence other = (PlusInfluence) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}.
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("6eaf2327-18a4-49ae-9df1-5ad3e085a208")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("52eb9a16-40ab-4f80-a505-1020ca94264c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("76734701-f94a-49ab-8f2f-4bd4a09b1648")
    protected PlusInfluence(Dependency elt) {
        this.elt = elt;
    }

    @objid ("5e2e10fd-0acf-4a71-9302-363201fdbdef")
    public static final class MdaTypes {
        @objid ("de944150-6222-410d-8260-54783c56e8cc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("17ded5be-f1e4-482b-b559-012c5dfdc73e")
        private static Stereotype MDAASSOCDEP;

        @objid ("bb84da21-68dd-426a-8c6d-7b676641bb00")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("80f98af2-9621-4487-9358-25e51a41f60d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0247-0000-000000000000");
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
