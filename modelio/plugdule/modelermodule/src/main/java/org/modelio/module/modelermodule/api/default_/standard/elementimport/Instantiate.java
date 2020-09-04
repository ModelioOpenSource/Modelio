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
 * Proxy class to handle a {@link ElementImport} with << instantiate >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1f52a2d6-89b9-4630-b892-b28a159590b9")
public class Instantiate {
    @objid ("9c6cd522-b268-44f8-962a-b31196295334")
    public static final String STEREOTYPE_NAME = "instantiate";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("c78c2af7-593c-4cbb-8595-b01b437a11b3")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Instantiate proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << instantiate >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1ba54c9a-25f1-41ff-9d5d-acf991982ae8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Instantiate.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << instantiate >> then instantiate a {@link Instantiate} proxy.
     * 
     * @return a {@link Instantiate} proxy on the created {@link ElementImport}.
     */
    @objid ("c6d627ed-3efb-4463-9ce3-891396113a91")
    public static Instantiate create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Instantiate.STEREOTYPE_NAME);
        return Instantiate.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Instantiate} proxy from a {@link ElementImport} stereotyped << instantiate >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Instantiate} proxy or <i>null</i>.
     */
    @objid ("79dcfb39-a174-42b9-8097-74da25447fca")
    public static Instantiate instantiate(ElementImport obj) {
        return Instantiate.canInstantiate(obj) ? new Instantiate(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Instantiate} proxy from a {@link ElementImport} stereotyped << instantiate >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Instantiate} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0dc57549-f61e-40d7-a7af-751055165055")
    public static Instantiate safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Instantiate.canInstantiate(obj))
        	return new Instantiate(obj);
        else
        	throw new IllegalArgumentException("Instantiate: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("340fa66d-812f-497d-9d58-9fc2b9f03bf5")
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
        Instantiate other = (Instantiate) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("4b9e43cf-e399-4223-acd0-fb0e3e8de12b")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("dd1d7067-b75a-4ed4-95ee-1b54e7a28d43")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ef916baa-73fb-45fc-9ea6-15f2bb0a287f")
    protected Instantiate(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("5005c1af-f43a-4d74-9d9a-355357fdf406")
    public static final class MdaTypes {
        @objid ("bed93ff2-c052-4c3f-8cd3-4a72e6cf0e75")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("90207ca9-a092-4ea2-934b-d64c93c10083")
        private static Stereotype MDAASSOCDEP;

        @objid ("d33ce596-151a-4818-8102-68a18fe82357")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a9c70c6a-25cf-4ea4-85d2-cb890716fc7c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01e5-0000-000000000000");
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
