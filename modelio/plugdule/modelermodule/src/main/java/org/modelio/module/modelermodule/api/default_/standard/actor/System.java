/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
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
 * Proxy class to handle a {@link Actor} with << system >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9fad6019-0f3e-455a-a1b5-1a961fdfdf59")
public class System {
    @objid ("93a6c3a8-a081-4b6f-8329-56d7420ae3b9")
    public static final String STEREOTYPE_NAME = "system";

    /**
     * The underlying {@link Actor} represented by this proxy, never null.
     */
    @objid ("91df8a31-b0f1-4d13-9788-0ca87aa33af9")
    protected final Actor elt;

    /**
     * Tells whether a {@link System proxy} can be instantiated from a {@link MObject} checking it is a {@link Actor} stereotyped << system >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c26e3236-cf79-427e-847d-acd9d59ed55d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Actor) && ((Actor) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, System.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Actor} stereotyped << system >> then instantiate a {@link System} proxy.
     * 
     * @return a {@link System} proxy on the created {@link Actor}.
     */
    @objid ("ad8d28c8-1813-49c4-ad85-312599601494")
    public static System create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Actor");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, System.STEREOTYPE_NAME);
        return System.instantiate((Actor)e);
    }

    /**
     * Tries to instantiate a {@link System} proxy from a {@link Actor} stereotyped << system >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Actor
     * @return a {@link System} proxy or <i>null</i>.
     */
    @objid ("9a96f9d8-af9b-460c-857f-97f284a6dd1c")
    public static System instantiate(Actor obj) {
        return System.canInstantiate(obj) ? new System(obj) : null;
    }

    /**
     * Tries to instantiate a {@link System} proxy from a {@link Actor} stereotyped << system >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Actor}
     * @return a {@link System} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("042286d1-763c-4b75-8bf0-016a4090e481")
    public static System safeInstantiate(Actor obj) throws IllegalArgumentException {
        if (System.canInstantiate(obj))
        	return new System(obj);
        else
        	throw new IllegalArgumentException("System: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("950d2f24-216d-4d66-90e9-7cefc5852ebe")
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
        System other = (System) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Actor}. 
     * @return the Actor represented by this proxy, never null.
     */
    @objid ("5a48db2d-47b1-4f4b-91d2-ab3d02aeb795")
    public Actor getElement() {
        return this.elt;
    }

    @objid ("f427a50b-cd29-484f-ae72-702c4fcb6a6a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f57fb7c5-4f4c-473c-b10d-95a7dfd90030")
    protected System(Actor elt) {
        this.elt = elt;
    }

    @objid ("2c237c05-ed2c-460e-a2b5-b7d3a6a403e1")
    public static final class MdaTypes {
        @objid ("86da2e19-c165-47b5-a280-3e2d4e2d5c0a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("98e12670-2c42-48b1-bc17-bb53620306a9")
        private static Stereotype MDAASSOCDEP;

        @objid ("ca0a9400-3fd6-4ee5-8b00-cae82a1c1e2c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("314e1e1b-b2b1-467b-bb1a-6ee4de78bfdc")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1ac4-0000-2f09-0000-000000000000");
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
