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
 * Proxy class to handle a {@link Dependency} with << antonym >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e3e8030d-e474-473c-b754-e7e5465e68e7")
public class Antonym {
    @objid ("1d032670-e0d2-40fb-b520-a29ec4e59fbb")
    public static final String STEREOTYPE_NAME = "antonym";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("c4837453-2df5-4f1a-bc07-f26b0957cc53")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Antonym proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << antonym >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("a3f4b8ad-bff7-4c36-8ba4-34b1b366c67b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Antonym.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << antonym >> then instantiate a {@link Antonym} proxy.
     * 
     * @return a {@link Antonym} proxy on the created {@link Dependency}.
     */
    @objid ("f157b509-b5f0-4f2d-a00c-49c787d70487")
    public static Antonym create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Antonym.STEREOTYPE_NAME);
        return Antonym.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Antonym} proxy from a {@link Dependency} stereotyped << antonym >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Antonym} proxy or <i>null</i>.
     */
    @objid ("3fe96c55-8f38-4865-b9f7-3100b0f231b5")
    public static Antonym instantiate(Dependency obj) {
        return Antonym.canInstantiate(obj) ? new Antonym(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Antonym} proxy from a {@link Dependency} stereotyped << antonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Antonym} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("44f57068-1bec-4a4d-8a4d-b4bc919e1376")
    public static Antonym safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Antonym.canInstantiate(obj))
        	return new Antonym(obj);
        else
        	throw new IllegalArgumentException("Antonym: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("fcd63648-27e1-40a1-976f-b0d5770e4b9b")
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
        Antonym other = (Antonym) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("1c399ed7-7106-4451-8df3-6bfffa71d894")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("9b82bb64-cf24-4f5e-8224-8ff0f7fff5b5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6f14105c-0f8a-4c3b-a8bb-64158af59988")
    protected Antonym(Dependency elt) {
        this.elt = elt;
    }

    @objid ("e8185a79-1f85-46e2-9ae1-2d7bcf9b15b1")
    public static final class MdaTypes {
        @objid ("145a78b9-fb7c-4d40-9626-356b44a27fff")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ab70aa8b-1c91-4891-9a23-f98ab45eca43")
        private static Stereotype MDAASSOCDEP;

        @objid ("d59961d9-987d-4ccc-8896-275c940dd3b5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("baa2b8cc-c1b7-4166-8bd5-53b09dcc0c42")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0233-0000-000000000000");
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
