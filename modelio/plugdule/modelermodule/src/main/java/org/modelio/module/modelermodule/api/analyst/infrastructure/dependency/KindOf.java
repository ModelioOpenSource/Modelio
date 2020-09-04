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
 * Proxy class to handle a {@link Dependency} with << kind-of >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("48e6ef94-b235-4f1d-8b9b-a5b2b6b40e53")
public class KindOf {
    @objid ("aca27807-61ca-43f4-82ce-771b5da813eb")
    public static final String STEREOTYPE_NAME = "kind-of";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("6b98ed16-411b-4ef7-a558-788c7e27fbc1")
    protected final Dependency elt;

    /**
     * Tells whether a {@link KindOf proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << kind-of >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("aad33b5f-fa39-4651-bded-f5e899947c67")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, KindOf.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << kind-of >> then instantiate a {@link KindOf} proxy.
     * 
     * @return a {@link KindOf} proxy on the created {@link Dependency}.
     */
    @objid ("39b8fb13-1761-4623-b2e4-315de8cde535")
    public static KindOf create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, KindOf.STEREOTYPE_NAME);
        return KindOf.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link KindOf} proxy from a {@link Dependency} stereotyped << kind-of >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link KindOf} proxy or <i>null</i>.
     */
    @objid ("3302e515-8f2f-4d5d-843f-cc807fcfd9b7")
    public static KindOf instantiate(Dependency obj) {
        return KindOf.canInstantiate(obj) ? new KindOf(obj) : null;
    }

    /**
     * Tries to instantiate a {@link KindOf} proxy from a {@link Dependency} stereotyped << kind-of >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link KindOf} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("4a2f2401-b7a2-4b97-93a8-fed6812a588d")
    public static KindOf safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (KindOf.canInstantiate(obj))
        	return new KindOf(obj);
        else
        	throw new IllegalArgumentException("KindOf: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5cc4eeb3-2366-4c11-b870-c3f6cf2a460f")
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
        KindOf other = (KindOf) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("ff9e2088-8e61-4041-87cd-a0cd5e06d48e")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("ecd6e8db-55e6-456f-af70-f8c4f742c587")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b6b3f6b9-833d-4931-8d1f-217b89ec773c")
    protected KindOf(Dependency elt) {
        this.elt = elt;
    }

    @objid ("5d095a89-ce1f-419e-b944-0225c5b7e954")
    public static final class MdaTypes {
        @objid ("903bdf66-d2aa-4a44-b59b-b86288ad6bc9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6773bfcf-815f-4a66-a7ae-ba70a5fd60b1")
        private static Stereotype MDAASSOCDEP;

        @objid ("6421bf5f-e36f-4793-8194-79b214ea85e5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("db4e274a-48f2-472e-807c-0b9368f11f93")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1228-0000-12f8-0000-000000000000");
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
