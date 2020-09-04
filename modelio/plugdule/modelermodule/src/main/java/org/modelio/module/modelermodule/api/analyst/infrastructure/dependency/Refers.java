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
 * Proxy class to handle a {@link Dependency} with << refers >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("263b586a-bce8-4894-8256-ff821796e9e9")
public class Refers {
    @objid ("c438f31b-e2d1-4f41-9958-d8596ce9e765")
    public static final String STEREOTYPE_NAME = "refers";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("22068266-57a1-461d-a182-bbf4bd120390")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Refers proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << refers >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6a87f27d-175a-4449-b0cd-93cc9a4ca58c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Refers.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << refers >> then instantiate a {@link Refers} proxy.
     * 
     * @return a {@link Refers} proxy on the created {@link Dependency}.
     */
    @objid ("a50f6114-4b9c-403d-b916-3659639f0bf3")
    public static Refers create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Refers.STEREOTYPE_NAME);
        return Refers.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Refers} proxy from a {@link Dependency} stereotyped << refers >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Refers} proxy or <i>null</i>.
     */
    @objid ("7acd91bc-0bbb-48a1-92a4-f0a40d4c0a82")
    public static Refers instantiate(Dependency obj) {
        return Refers.canInstantiate(obj) ? new Refers(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Refers} proxy from a {@link Dependency} stereotyped << refers >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Refers} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("29dfb26a-396d-464f-86fe-65a2f85ab2cb")
    public static Refers safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Refers.canInstantiate(obj))
        	return new Refers(obj);
        else
        	throw new IllegalArgumentException("Refers: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5e34d7bc-8aa8-4cd5-84bf-8c832e62013a")
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
        Refers other = (Refers) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("096a307c-80e9-452f-8b23-558965dfc40c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("c22e85f3-d2dd-4afa-8c41-d14b54a40407")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2851d787-b51d-4ec2-b2a3-7863d795ea0e")
    protected Refers(Dependency elt) {
        this.elt = elt;
    }

    @objid ("36fe2657-f445-48fc-a9c1-922300a50d98")
    public static final class MdaTypes {
        @objid ("96003283-6072-4dbf-9872-f27f23590138")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6a1dab8d-3191-4bef-b915-84866b7de27a")
        private static Stereotype MDAASSOCDEP;

        @objid ("72cf51d3-bbbe-4b8a-8f43-1e81a0e5056d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dc237204-9077-4ecc-80f2-e1033e2aab00")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0265-0000-000000000000");
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
