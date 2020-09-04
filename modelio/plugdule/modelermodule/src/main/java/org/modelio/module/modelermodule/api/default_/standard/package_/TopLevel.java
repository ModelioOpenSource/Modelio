/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << topLevel >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("18369814-6a88-4ede-86be-c5df9aba56d3")
public class TopLevel {
    @objid ("022b1a7f-1520-4423-9e91-c9a6525d753b")
    public static final String STEREOTYPE_NAME = "topLevel";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("fa0859b8-ef35-4afe-95dc-d63a865668b3")
    protected final Package elt;

    /**
     * Tells whether a {@link TopLevel proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << topLevel >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("38e8d96b-e44a-4f9a-8d50-a1590eef99e6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, TopLevel.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << topLevel >> then instantiate a {@link TopLevel} proxy.
     * 
     * @return a {@link TopLevel} proxy on the created {@link Package}.
     */
    @objid ("01526f85-84ea-4a0f-a0a9-d980946cc2cd")
    public static TopLevel create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, TopLevel.STEREOTYPE_NAME);
        return TopLevel.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link TopLevel} proxy from a {@link Package} stereotyped << topLevel >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link TopLevel} proxy or <i>null</i>.
     */
    @objid ("bd64ff94-a2a0-464f-b204-561504931217")
    public static TopLevel instantiate(Package obj) {
        return TopLevel.canInstantiate(obj) ? new TopLevel(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TopLevel} proxy from a {@link Package} stereotyped << topLevel >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link TopLevel} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("21b20712-bd42-438e-be2d-5e128fd49a32")
    public static TopLevel safeInstantiate(Package obj) throws IllegalArgumentException {
        if (TopLevel.canInstantiate(obj))
        	return new TopLevel(obj);
        else
        	throw new IllegalArgumentException("TopLevel: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("355c2240-2c73-4195-b93d-4d205919d0c0")
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
        TopLevel other = (TopLevel) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("08cca707-1865-4cf5-8858-419c1a7eea69")
    public Package getElement() {
        return this.elt;
    }

    @objid ("16e7051e-53f1-4125-8f5c-dbd5cd24c342")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ba201f52-b8b0-4a39-8f02-81128d395042")
    protected TopLevel(Package elt) {
        this.elt = elt;
    }

    @objid ("8002304f-2a7d-47cc-b5a1-1cda6ca608d8")
    public static final class MdaTypes {
        @objid ("4b1f4e5e-2c9f-437e-9f24-8024da2c0c38")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5dbd2c8d-f208-4649-ae88-03eaec4e7b05")
        private static Stereotype MDAASSOCDEP;

        @objid ("169987de-6e02-42c0-b813-27af5c2c9ba1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9b7d5a3f-12ff-4159-a276-694488cb8b63")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d9-0000-000000000000");
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
