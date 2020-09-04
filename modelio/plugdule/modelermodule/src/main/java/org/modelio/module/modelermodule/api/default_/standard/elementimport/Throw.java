/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.elementimport;

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
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link ElementImport} with << throw >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("18da7ea1-47b8-4eef-93d1-8ff145826e37")
public class Throw {
    @objid ("744ae113-a538-4be4-9c2e-61d988c3a270")
    public static final String STEREOTYPE_NAME = "throw";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("0016554e-1771-44d0-b0a1-ee247f0af0e3")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Throw proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << throw >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("dc7ae278-f97c-4e50-bc2c-2eb5ba711003")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Throw.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << throw >> then instantiate a {@link Throw} proxy.
     * 
     * @return a {@link Throw} proxy on the created {@link ElementImport}.
     */
    @objid ("596fb79f-ddbe-400b-98a1-4bbf046c6db7")
    public static Throw create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Throw.STEREOTYPE_NAME);
        return Throw.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Throw} proxy from a {@link ElementImport} stereotyped << throw >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Throw} proxy or <i>null</i>.
     */
    @objid ("ad653693-76f4-40fb-9b01-3ceee744ce38")
    public static Throw instantiate(ElementImport obj) {
        return Throw.canInstantiate(obj) ? new Throw(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Throw} proxy from a {@link ElementImport} stereotyped << throw >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Throw} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("93f601d9-4e21-46f4-bc3c-46ffc1c43f75")
    public static Throw safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Throw.canInstantiate(obj))
        	return new Throw(obj);
        else
        	throw new IllegalArgumentException("Throw: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("04c6c01c-9bf1-4494-bc71-ee36bd500bd7")
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
        Throw other = (Throw) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("7a67baca-79f0-4925-80b2-8673846ac0a8")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("89d8b9f1-c17d-4c81-aa98-324ed498253e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a2fd390c-a34e-4c8d-a026-d57a7beabbaf")
    protected Throw(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("1f8f9f45-0963-4c94-b96b-4765073e15e0")
    public static final class MdaTypes {
        @objid ("b7d3401b-ac06-433c-b881-c3523b821e35")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9e021911-7a73-4d77-a1d5-57aa069c2fcc")
        private static Stereotype MDAASSOCDEP;

        @objid ("fa4fdc8d-cef1-4203-88f3-e0d33bc6c8d1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("585f16a5-bca3-4d64-b8bd-a63e1d128d98")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0054070c-0000-005d-0000-000000000000");
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
