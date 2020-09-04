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
 * Proxy class to handle a {@link ElementImport} with << catch >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("28e7a074-1463-4ea2-b62c-fcf073d01c3e")
public class Catch {
    @objid ("7ae825ff-a481-4a21-b539-6b2bde89cc9f")
    public static final String STEREOTYPE_NAME = "catch";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("11a779a8-8a8e-4142-9839-3b6855931dab")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Catch proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << catch >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9190a344-ac6c-489f-b1e9-9a528dc0afe4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Catch.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << catch >> then instantiate a {@link Catch} proxy.
     * 
     * @return a {@link Catch} proxy on the created {@link ElementImport}.
     */
    @objid ("4cddebf3-d5b6-48a7-823b-2053b831e4d3")
    public static Catch create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Catch.STEREOTYPE_NAME);
        return Catch.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Catch} proxy from a {@link ElementImport} stereotyped << catch >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Catch} proxy or <i>null</i>.
     */
    @objid ("55328d93-8af0-4611-832d-beecf32b92f2")
    public static Catch instantiate(ElementImport obj) {
        return Catch.canInstantiate(obj) ? new Catch(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Catch} proxy from a {@link ElementImport} stereotyped << catch >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Catch} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f3de2f99-fd4a-470b-869c-a9ab75651123")
    public static Catch safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Catch.canInstantiate(obj))
        	return new Catch(obj);
        else
        	throw new IllegalArgumentException("Catch: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("fb5ce6b8-48ac-4ac6-bb1c-fa35bfd2bde7")
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
        Catch other = (Catch) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("c598b122-bc89-4e1d-9d14-ec633edc8982")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("487d5f6a-edd5-4282-a82c-684e336edd40")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("46c89d65-30ad-4f43-a36e-fcac62c8d239")
    protected Catch(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("fa9f3ff0-e1c4-4c57-be5e-a56fb2fb5e4f")
    public static final class MdaTypes {
        @objid ("98704d87-d069-4a64-ae5c-aa8c98b352bd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a201266e-8528-4acd-a338-e30779b6a347")
        private static Stereotype MDAASSOCDEP;

        @objid ("95e4175c-bfcf-4b04-a2c9-db6a494fa1e2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("646364c2-78d9-40ff-879c-a230eef362df")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0054070c-0000-005f-0000-000000000000");
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
