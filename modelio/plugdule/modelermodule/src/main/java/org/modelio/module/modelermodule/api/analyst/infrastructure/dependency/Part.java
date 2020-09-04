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
 * Proxy class to handle a {@link Dependency} with << part >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5b672df6-1d49-4ad4-890a-08c3ae1afcb3")
public class Part {
    @objid ("139d420d-8c8b-427d-b558-538cac0dd61f")
    public static final String STEREOTYPE_NAME = "part";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("28d46e3e-a326-4674-b39f-a6f2c4bc11a9")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Part proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << part >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("ed406820-36a9-4828-9136-1f461546d324")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Part.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << part >> then instantiate a {@link Part} proxy.
     * 
     * @return a {@link Part} proxy on the created {@link Dependency}.
     */
    @objid ("b76df357-8c8e-4394-a662-e789bbddad38")
    public static Part create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Part.STEREOTYPE_NAME);
        return Part.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Part} proxy from a {@link Dependency} stereotyped << part >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Part} proxy or <i>null</i>.
     */
    @objid ("74aa1e6d-56c7-4dcd-89c0-6db399ba42de")
    public static Part instantiate(Dependency obj) {
        return Part.canInstantiate(obj) ? new Part(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Part} proxy from a {@link Dependency} stereotyped << part >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Part} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("dd11fb24-aeb0-44ac-a579-cb61c1d48ac5")
    public static Part safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Part.canInstantiate(obj))
        	return new Part(obj);
        else
        	throw new IllegalArgumentException("Part: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("52b75cf7-4999-400f-b7e9-3b9a51b135dc")
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
        Part other = (Part) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("d20eb5e6-4536-4c6b-950d-cf041ae06b38")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4e806782-c4f9-4f9e-a9b5-47c92a748d5b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("bd8c87b0-0ed8-48c1-b308-5405c7491c25")
    protected Part(Dependency elt) {
        this.elt = elt;
    }

    @objid ("7ea483f7-ea8b-4bc8-8c23-87eb77766490")
    public static final class MdaTypes {
        @objid ("e088b808-9006-4c6a-b001-6acbbfb1a021")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c4ebe7a3-3095-4080-8d15-312a699f291a")
        private static Stereotype MDAASSOCDEP;

        @objid ("dc8e0667-0c31-4281-af48-2a40be5c80fc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("264aa216-d058-47dd-afde-232be6f30436")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-00b7-0000-000000000000");
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
