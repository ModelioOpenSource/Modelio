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
 * Proxy class to handle a {@link Dependency} with << synonym >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("85cec5dc-8795-48b4-b7c1-726492848407")
public class Synonym {
    @objid ("80d84345-4d4d-4c90-826d-c771dca176c1")
    public static final String STEREOTYPE_NAME = "synonym";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("280dca40-ae55-4ab9-a6f2-a9e11966e9c1")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Synonym proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << synonym >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d0e87d89-f63c-48ee-beae-a40a71dfd7ce")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Synonym.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << synonym >> then instantiate a {@link Synonym} proxy.
     * 
     * @return a {@link Synonym} proxy on the created {@link Dependency}.
     */
    @objid ("dc255dee-9853-46d0-8e32-197598e1b0a0")
    public static Synonym create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Synonym.STEREOTYPE_NAME);
        return Synonym.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Synonym} proxy from a {@link Dependency} stereotyped << synonym >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Synonym} proxy or <i>null</i>.
     */
    @objid ("7d0b8d04-ede4-4afa-84c1-83e84117df75")
    public static Synonym instantiate(Dependency obj) {
        return Synonym.canInstantiate(obj) ? new Synonym(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Synonym} proxy from a {@link Dependency} stereotyped << synonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Synonym} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6cd2fd66-9f40-4987-a574-77ba62ffdf5c")
    public static Synonym safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Synonym.canInstantiate(obj))
        	return new Synonym(obj);
        else
        	throw new IllegalArgumentException("Synonym: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("53ff1935-b8dc-4947-9ca6-18cf233a7b62")
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
        Synonym other = (Synonym) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("7f9889b1-9061-4ab4-a8d4-f1ea84722dc2")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("05434d42-ca3f-4610-8067-7be8463b6611")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c124b473-ebdb-46eb-b060-843a1969e623")
    protected Synonym(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b89f217e-7c42-4e09-b7aa-7f322e65a884")
    public static final class MdaTypes {
        @objid ("8cf2816e-2f09-4e51-8d97-e582651dc758")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("efb0548e-7d96-4da5-8579-ba5e977a40c5")
        private static Stereotype MDAASSOCDEP;

        @objid ("04a36dbb-1aee-48d1-973b-ed603c30aeb7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5c828339-f14e-491d-9096-6014f078301f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-022e-0000-000000000000");
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
