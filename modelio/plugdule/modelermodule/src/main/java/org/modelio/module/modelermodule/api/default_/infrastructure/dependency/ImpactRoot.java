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
 * Proxy class to handle a {@link Dependency} with << impact_root >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3ffb8a66-6e57-4ac5-9cfc-a8913d48cb7a")
public class ImpactRoot {
    @objid ("52a5792a-6df5-41cf-8a1f-785ca233ceaf")
    public static final String STEREOTYPE_NAME = "impact_root";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("1e99fb95-754f-41e7-9a86-4d23464a38b5")
    protected final Dependency elt;

    /**
     * Tells whether a {@link ImpactRoot proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << impact_root >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("941347ca-0031-47fc-9c49-6b720b6daa18")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImpactRoot.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << impact_root >> then instantiate a {@link ImpactRoot} proxy.
     * 
     * @return a {@link ImpactRoot} proxy on the created {@link Dependency}.
     */
    @objid ("7146dd47-2613-4c22-b55b-8bc1be94ded2")
    public static ImpactRoot create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImpactRoot.STEREOTYPE_NAME);
        return ImpactRoot.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link ImpactRoot} proxy from a {@link Dependency} stereotyped << impact_root >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link ImpactRoot} proxy or <i>null</i>.
     */
    @objid ("b2bc31ae-6a43-4c2e-a27b-d73b5d8d0730")
    public static ImpactRoot instantiate(Dependency obj) {
        return ImpactRoot.canInstantiate(obj) ? new ImpactRoot(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImpactRoot} proxy from a {@link Dependency} stereotyped << impact_root >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link ImpactRoot} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("32713256-c40c-4d15-8235-c662e6ac8c20")
    public static ImpactRoot safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (ImpactRoot.canInstantiate(obj))
        	return new ImpactRoot(obj);
        else
        	throw new IllegalArgumentException("ImpactRoot: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e4139230-5d1f-4e75-92f2-fb942f2065f0")
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
        ImpactRoot other = (ImpactRoot) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("38202871-6eab-46a4-8744-72e6bd20fd22")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("8c1b5883-2079-4f9e-aa89-fa7af18f0af8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("06099a6b-82eb-49f0-9ecc-7a66fdfaab6a")
    protected ImpactRoot(Dependency elt) {
        this.elt = elt;
    }

    @objid ("40cc123a-c9ad-4e12-9d54-e544e33ec0f4")
    public static final class MdaTypes {
        @objid ("f698f084-6d9b-42c0-beb8-7228e68ed5b1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("018f4993-083a-4340-9e87-66f3bf94b0e4")
        private static Stereotype MDAASSOCDEP;

        @objid ("f4a566f8-948d-4818-831c-d94960808d24")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d59a3359-2108-446c-b7ca-963508f1f681")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec2468-0000-0ac1-0000-000000000000");
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
