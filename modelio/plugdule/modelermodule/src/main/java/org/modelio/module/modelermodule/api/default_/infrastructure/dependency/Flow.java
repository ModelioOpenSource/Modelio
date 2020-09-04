/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << flow >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("059d7606-63e7-4b15-b47f-42f41353ce1e")
public class Flow {
    @objid ("a4f247e4-e587-4898-98f8-32f917ba8f38")
    public static final String STEREOTYPE_NAME = "flow";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("bcd7b1a2-b79b-4672-981b-e68e7c559af8")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Flow proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << flow >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("79c5df06-297f-4aa8-8f0b-b6ca178031e4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Flow.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << flow >> then instantiate a {@link Flow} proxy.
     * 
     * @return a {@link Flow} proxy on the created {@link Dependency}.
     */
    @objid ("a50f11e6-56dc-46f6-8f01-fc11c76ff38b")
    public static Flow create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Flow.STEREOTYPE_NAME);
        return Flow.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Flow} proxy from a {@link Dependency} stereotyped << flow >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Flow} proxy or <i>null</i>.
     */
    @objid ("b8676564-e76c-4ed7-9921-eeacf27dec5a")
    public static Flow instantiate(Dependency obj) {
        return Flow.canInstantiate(obj) ? new Flow(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Flow} proxy from a {@link Dependency} stereotyped << flow >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Flow} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("37ab9653-838c-4585-a86e-33a00196ba05")
    public static Flow safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Flow.canInstantiate(obj))
        	return new Flow(obj);
        else
        	throw new IllegalArgumentException("Flow: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8e467641-3544-47fd-888e-75bdefa30d09")
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
        Flow other = (Flow) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("ec5849ac-c4fe-4e0f-b36d-8c4f11ac6347")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("3185f4ad-bdc6-4f28-b67a-b57ce9afaec9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("73dc3538-b57d-41ff-8677-3856ac622e9b")
    protected Flow(Dependency elt) {
        this.elt = elt;
    }

    @objid ("82b9b25a-72f2-40a9-9d30-45b5eb452746")
    public static final class MdaTypes {
        @objid ("406470ca-a7d3-4a41-aac0-4d2314ade948")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fd9c0286-e552-4a51-a5de-66d222152cb1")
        private static Stereotype MDAASSOCDEP;

        @objid ("396ee1d3-ef30-4399-9a17-88fa91e0e71e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("007c5815-0e75-4920-98a8-0adcf2ce0b46")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1228-0000-0964-0000-000000000000");
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
