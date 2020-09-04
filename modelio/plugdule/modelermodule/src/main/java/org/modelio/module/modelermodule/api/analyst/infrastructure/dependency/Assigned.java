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
 * Proxy class to handle a {@link Dependency} with << assigned >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1f6b5b83-851b-4d23-b980-2de82874fa5b")
public class Assigned {
    @objid ("35fcd104-28dc-41cf-a860-06cb1e049d2c")
    public static final String STEREOTYPE_NAME = "assigned";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("61bf2720-2ce3-4190-95e3-871cd3080b51")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Assigned proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << assigned >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0369f054-2e8e-45b1-b94c-52bad3d091a9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Assigned.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << assigned >> then instantiate a {@link Assigned} proxy.
     * 
     * @return a {@link Assigned} proxy on the created {@link Dependency}.
     */
    @objid ("e14c3763-0ad7-4a24-a813-11ee223b9eb5")
    public static Assigned create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Assigned.STEREOTYPE_NAME);
        return Assigned.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Assigned} proxy from a {@link Dependency} stereotyped << assigned >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Assigned} proxy or <i>null</i>.
     */
    @objid ("be420c23-f677-4119-bb02-4b1b24d4a4bd")
    public static Assigned instantiate(Dependency obj) {
        return Assigned.canInstantiate(obj) ? new Assigned(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Assigned} proxy from a {@link Dependency} stereotyped << assigned >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Assigned} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1e8eb49f-afdf-4268-b786-3862295a4577")
    public static Assigned safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Assigned.canInstantiate(obj))
        	return new Assigned(obj);
        else
        	throw new IllegalArgumentException("Assigned: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("035a7f9a-24d3-4532-8abf-d91f9c5974f2")
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
        Assigned other = (Assigned) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("07774a61-c0ac-4de5-b4cb-556e4838903c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("ab9d05b4-79c5-446d-b182-11878f5bb775")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c6256b43-1bf0-49f0-9e16-0b7d7f177dff")
    protected Assigned(Dependency elt) {
        this.elt = elt;
    }

    @objid ("52e42aa6-442a-4a00-a707-08c1820fc9cd")
    public static final class MdaTypes {
        @objid ("12f01666-9993-4507-8e7d-d352592e76c4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("11d6c549-b420-4355-929a-351a763952b3")
        private static Stereotype MDAASSOCDEP;

        @objid ("993a58ee-4dc5-4a76-bdc2-83a95055a574")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ca60e4bd-4967-4221-b8ff-b546c72031f4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-025b-0000-000000000000");
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
