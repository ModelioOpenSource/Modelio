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
 * Proxy class to handle a {@link Dependency} with << homonym >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ee86437c-78c7-4033-9a77-f40e04f46719")
public class Homonym {
    @objid ("9ec4e499-f140-4eec-8a28-e239b4a509cb")
    public static final String STEREOTYPE_NAME = "homonym";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("a0b1b938-5325-44dc-8103-5ba7eeca7e11")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Homonym proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << homonym >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c0643586-01fe-4c21-82ea-1fc331c83bb7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Homonym.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << homonym >> then instantiate a {@link Homonym} proxy.
     * 
     * @return a {@link Homonym} proxy on the created {@link Dependency}.
     */
    @objid ("dd4b8e3b-78ce-47a9-a4f2-e850e8a07a09")
    public static Homonym create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Homonym.STEREOTYPE_NAME);
        return Homonym.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Homonym} proxy from a {@link Dependency} stereotyped << homonym >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Homonym} proxy or <i>null</i>.
     */
    @objid ("90638e20-9a3b-4d41-843a-f2b86209e6db")
    public static Homonym instantiate(Dependency obj) {
        return Homonym.canInstantiate(obj) ? new Homonym(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Homonym} proxy from a {@link Dependency} stereotyped << homonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Homonym} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3cb0e0b7-6a3b-4ea3-b86f-132cda331643")
    public static Homonym safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Homonym.canInstantiate(obj))
        	return new Homonym(obj);
        else
        	throw new IllegalArgumentException("Homonym: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("075bdbbf-d86b-408d-a194-9bc672a54cea")
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
        Homonym other = (Homonym) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("6774bcee-661d-463b-9499-de1ee6fe1e05")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("be959aba-8769-4e88-b8e4-4a8c8e16d381")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("20b402d3-49a7-4d30-a568-4a033e01d7d2")
    protected Homonym(Dependency elt) {
        this.elt = elt;
    }

    @objid ("8225eb66-4057-4d15-8d1d-414b4382bf6b")
    public static final class MdaTypes {
        @objid ("01ea4ae6-3d95-4563-aa6c-b4187bdd1bf9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f9cc3365-109d-4bc7-9264-1e89b21d821e")
        private static Stereotype MDAASSOCDEP;

        @objid ("4a44aa21-8159-4fa6-a502-36a321a612ec")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a681aacb-d7af-480d-a1f7-94156bbc7a4a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0238-0000-000000000000");
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
