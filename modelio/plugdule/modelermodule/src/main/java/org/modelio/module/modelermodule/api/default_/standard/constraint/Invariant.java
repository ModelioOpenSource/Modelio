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
 * Proxy class to handle a {@link Constraint} with << invariant >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e56d6561-c974-4569-834a-dbe37b352acd")
public class Invariant {
    @objid ("2c931dd4-c13d-48db-aaf0-a9b7b4545a11")
    public static final String STEREOTYPE_NAME = "invariant";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("f9fb7dcc-dc1f-4d73-ad3b-cfcdd22e9b4a")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Invariant proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << invariant >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b3ab8ee1-5717-4149-86a4-76e7851d325d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Invariant.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << invariant >> then instantiate a {@link Invariant} proxy.
     * 
     * @return a {@link Invariant} proxy on the created {@link Constraint}.
     */
    @objid ("4c470f13-1ec6-40df-b49c-a3013df18805")
    public static Invariant create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Invariant.STEREOTYPE_NAME);
        return Invariant.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Invariant} proxy from a {@link Constraint} stereotyped << invariant >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Invariant} proxy or <i>null</i>.
     */
    @objid ("f3d416d3-6dcd-4771-9076-5bff11a9ba99")
    public static Invariant instantiate(Constraint obj) {
        return Invariant.canInstantiate(obj) ? new Invariant(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Invariant} proxy from a {@link Constraint} stereotyped << invariant >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Invariant} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d9078774-1cf3-47b1-a0b1-fb5b1852d287")
    public static Invariant safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Invariant.canInstantiate(obj))
        	return new Invariant(obj);
        else
        	throw new IllegalArgumentException("Invariant: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d9389664-1ddb-425e-ba6c-2212493c3ff7")
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
        Invariant other = (Invariant) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("91b5054b-e4e7-434f-8380-abcae6d26100")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("0f229008-6abd-4556-9233-a87844f7d0ce")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c4c1aec8-7481-4090-9d63-5ddb14f8b9d9")
    protected Invariant(Constraint elt) {
        this.elt = elt;
    }

    @objid ("2ffe4bc5-f27a-4f95-9bdc-ffea03ee62b0")
    public static final class MdaTypes {
        @objid ("a2b3c93b-3660-49b3-9055-45bd500a53b6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4363e656-fc5f-44e7-8c53-9d36a9272422")
        private static Stereotype MDAASSOCDEP;

        @objid ("15384991-7c3a-48bd-ba84-8445b8b81b2a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("620b18e5-8d22-4535-b06d-3d7912b3f43a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c44-0000-000000000000");
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
