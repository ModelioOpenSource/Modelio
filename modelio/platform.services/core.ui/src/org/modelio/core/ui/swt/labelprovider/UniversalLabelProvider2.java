/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.core.ui.swt.labelprovider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.core.ui.swt.images.ElementStyler;
import org.modelio.core.ui.swt.images.FragmentImageService;
import org.modelio.core.ui.swt.images.FragmentStyledLabelProvider;
import org.modelio.core.ui.swt.images.IModelioElementLabelProvider;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.ui.CoreFontRegistry;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Browser tree label provider delegating part of its job to <i>extension</i> label providers coming with metamodel fragments.
 */
@objid ("5571e6ef-a4b7-4c7c-8876-3f63966ca4ae")
public class UniversalLabelProvider2 extends LabelProvider implements IModelioElementLabelProvider {
    @objid ("ad09bb75-846f-4771-b121-e20357e24c5b")
    private static final String LABEL_PROVIDER_EXTENSION_POINT = "org.modelio.app.metamodel.browser.labelprovider";

    @objid ("2d312b47-d304-4ba5-bb20-419c52aecf54")
    protected BrowserLabelService umlLabelService;

    @objid ("a2937331-089c-4a88-aeb1-755c79dbbc40")
    protected static final Font normalFont = CoreFontRegistry.getFont(Display.getCurrent().getSystemFont().getFontData());

    @objid ("5d1dfaf1-5de0-428c-817f-4244d8753190")
    protected static final Font italicFont = CoreFontRegistry.getModifiedFont(UniversalLabelProvider2.normalFont, SWT.ITALIC, 1.0f);

    @objid ("2b624f23-7227-4bd6-887f-7ee611574dd5")
    private Map<String, IModelioElementLabelProvider> extensions = new HashMap<>();

    @objid ("03adfe76-35e7-4020-ada6-f614770cddff")
    private IRegistryEventListener listener;

    /**
     * Default c'tor.
     */
    @objid ("0ce39571-4580-45d1-9d59-6762c82d1bc8")
    public UniversalLabelProvider2() {
        this.umlLabelService = new BrowserLabelService();
        
        IExtensionRegistry registry = RegistryFactory.getRegistry();
        this.listener = new MmServicesListener();
        
        registry.addListener(this.listener, UniversalLabelProvider2.LABEL_PROVIDER_EXTENSION_POINT);
        
        IConfigurationElement[] cfels = registry.getConfigurationElementsFor(UniversalLabelProvider2.LABEL_PROVIDER_EXTENSION_POINT);
        addExtensionElements(cfels);
    }

    @objid ("142f93b2-80bf-478a-9228-62437c1d017b")
    @Override
    public Image getImage(Object obj) {
        // null object special case
        if (obj == null) {
            return null;
        } else if (obj instanceof IProjectFragment) {
            return FragmentImageService.getImage((IProjectFragment) obj);
        } else if (obj instanceof IModelContainer) {
            return ((IModelContainer<?>) obj).getIcon();
        } else if (obj instanceof MObject) {
            IModelioElementLabelProvider ext = this.extensions.get(((MObject) obj).getMClass().getOrigin().getName());
            if (ext != null) {
                return ext.getImage(obj);
            } else if (obj instanceof MObject) {
                return ElementImageService.getIcon((MObject) obj);
            }
        } else {
            for (IModelioElementLabelProvider lbp : this.extensions.values()) {
                Image img = lbp.getImage(obj);
                if (img != null) {
                    return img;
                }
            }
        }
        return null;
    }

    @objid ("90e03fce-ebdb-4880-9b7e-a09139c1e6e5")
    @Override
    public StyledString getStyledText(Object obj) {
        if (obj == null) {
            return new StyledString("<null>", StyledString.createColorRegistryStyler("red", null));
        } else if (obj instanceof IProjectFragment) {
            return FragmentStyledLabelProvider.getStyledText((IProjectFragment) obj);
        } else if (obj instanceof IModelContainer) {
            return new StyledString(((IModelContainer<?>) obj).getLabel());
        } else if (obj instanceof MObject) {
            IModelioElementLabelProvider ext = this.extensions.get(((MObject) obj).getMClass().getOrigin().getName());
            StyledString styledText = ext != null ? ext.getStyledText(obj) : null;
            if (styledText != null) {
                return styledText;
            } else if (obj instanceof MObject) {
                return this.umlLabelService.getLabel((MObject) obj);
            }
        } else {
            for (IModelioElementLabelProvider lbp : this.extensions.values()) {
                StyledString styledText = lbp.getStyledText(obj);
                if (styledText != null) {
                    return styledText;
                }
            }
        }
        return new StyledString(obj.toString(), StyledString.createColorRegistryStyler("red", null));
    }

    /**
     * Returns the simple label of an object, without style additions. Uses
     * {@link BrowserLabelProvider#getStyledText(Object)}.
     */
    @objid ("783f5d77-ab85-454e-bacf-98022274ee64")
    @Override
    public String getText(Object element) {
        return getStyledText(element).getString();
    }

    @objid ("abc8fe0d-4a8c-482a-b004-f7c20745ccb6")
    @Override
    public boolean showAsReference(Object object) {
        return false;
    }

    @objid ("342396b3-67d9-4968-990b-738efe23cb72")
    public void registerExtension(String fragmentName, IModelioElementLabelProvider contentProvider) {
        this.extensions.put(fragmentName, contentProvider);
    }

    @objid ("ac26b245-3fb8-4da1-8d0e-79f2ee909357")
    public void unregisterExtension(String fragmentName) {
        this.extensions.remove(fragmentName);
    }

    @objid ("60aa5d5c-0b5a-40b0-adb7-10def4f4ac2b")
    @Override
    public void dispose() {
        IExtensionRegistry registry = RegistryFactory.getRegistry();
        if (registry != null) {
            registry.removeListener(this.listener);
        }
    }

    /**
     * Unregister the services that were registered with {@link #addExtensionElements(IConfigurationElement[])}.
     * @see #addExtensionElements(IConfigurationElement[])
     * 
     * @param configurationElements the configuration elements to remove.
     */
    @objid ("9a4ec9cf-857e-46c3-9402-941813bc41c1")
    protected void removeExtensionElements(IConfigurationElement[] configurationElements) {
        for (IConfigurationElement cf : configurationElements) {
            if (cf.getName().equals("services")) {
                for (IConfigurationElement svcEl : cf.getChildren("service")) {
                    String mmf = svcEl.getAttribute("metamodel");
                    unregisterExtension(mmf);
                }
            }
        }
    }

    /**
     * Register the metamodel related services declared in the given {@link IConfigurationElement} trees.
     * <p>
     * The default implementation looks for "service" child elements with:
     * <ul>
     * <li>a "metamodel" string attribute
     * <li>a "implementation" string attribute representing a java class accessible by the declaring plugin.
     * </ul>
     * 
     * @param configurationElements the {@link IConfigurationElement} at the root of the matched plugin extensions.
     */
    @objid ("c0ff2b9c-9f83-4aff-98b1-ee6cd915030f")
    protected void addExtensionElements(IConfigurationElement[] configurationElements) {
        for (IConfigurationElement ce : configurationElements) {
            String mmf = ce.getAttribute("metamodel");
        
            try {
                IModelioElementLabelProvider svc = (IModelioElementLabelProvider) ce.createExecutableExtension("implementation");
                registerExtension(mmf, svc);
            } catch (CoreException e) {
                CoreUi.LOG.error("Failed registering '%s' service provided by '%s' for '%s' metamodel: %s",
                        ce.getAttribute("implementation"),
                        ce.getContributor().getName(),
                        mmf,
                        e.getMessage());
                CoreUi.LOG.error(e);
            }
        }
    }

    @objid ("7b1b5255-e08b-4003-838d-ef5b5f9ab28d")
    public IModelioElementLabelProvider getExtension(String mmFragmentName) {
        return this.extensions.get(mmFragmentName);
    }

    /**
     * This class provide the label of the elements displayed in the UML
     * explorer
     */
    @objid ("20db0f07-3c7b-4c60-a0ce-132d7804c100")
    private static class BrowserLabelService extends DefaultInfrastructureVisitor {
        /**
         * a stack used for recursive calls to
         * {@link #getLabel(Element, boolean)}
         */
        @objid ("5c961b6a-a73a-43c1-bfb3-202ab312f944")
        private final Stack<MObject> elementStack;

        @objid ("c8dc96a2-c85a-460e-8c3b-1206e5df526a")
        public BrowserLabelService() {
            this(new Stack<MObject>());
        }

        /**
         * Get the explorer label for the given element.
         * @param featuresVisibility Whether or not to show the visibility in feature's labels.
         * @param namespaceVisibility Whether or not to show the visibility in namespace's
         * labels.
         * 
         * @param element The element to get symbol
         * @return The element symbol.
         */
        @objid ("523949cd-d6aa-4008-aa5e-6869ab3f0169")
        public StyledString getLabel(MObject element) {
            // Guard agains't null elements
            if (element == null) {
                return new StyledString("<null>", ElementStyler.getStyler(null));
            }
            
            if (this.elementStack.contains(element)) {
                // loop detected, return the name...
                return new StyledString(element.getName());
            }
            
            // store the element for loop detection, push context
            this.elementStack.push(element);
            
            try {
                // call the visitor
                return (StyledString) element.accept(this);
            } finally {
                this.elementStack.pop();
            }
        }

        @objid ("124c258f-bf16-4881-b699-c654c7e68cb2")
        @Override
        public Object visitDependency(Dependency theDependency) {
            final ModelElement destination = theDependency.getDependsOn();
            
            assert Dependency.class == theDependency.getMClass().getJavaInterface();
            return visitDependencyLikeObject(theDependency, "depends on", destination);
        }

        @objid ("af511c9e-a47e-4915-8740-d6376ae2798f")
        @Override
        public Object visitElement(Element theElement) {
            return new StyledString(theElement.getClass().getSimpleName(), ElementStyler.getStyler(theElement));
        }

        @objid ("877482c5-9492-4b3c-862b-b6a923929be0")
        @Override
        public Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
            return new StyledString(CoreUi.I18N.getMessage("MatrixValueDefinition.Values"), ElementStyler.getStyler(obj));
        }

        @objid ("6ea32995-bbba-4a55-824f-911b3101acc9")
        @Override
        public Object visitMetaclassReference(MetaclassReference theMataclassReference) {
            final String ref = theMataclassReference.getReferencedClassName();
            final Styler styler = ElementStyler.getStyler(theMataclassReference);
            if (ref != null) {
                return new StyledString(ref, styler);
            } else {
                return new StyledString("<null>", styler);
            }
        }

        @objid ("24beb65e-6574-4cae-9c9c-cb59bd587346")
        @Override
        public Object visitModelElement(ModelElement theModelElement) {
            return new StyledString(theModelElement.getName(), ElementStyler.getStyler(theModelElement));
        }

        @objid ("fbe11a3c-4249-4ef2-99f9-70c40ce3d36e")
        @Override
        public Object visitNote(Note obj) {
            final StyledString symbol = new StyledString();
            final Styler style = ElementStyler.getStyler(obj);
            
            NoteType model = obj.getModel();
            String noteType = model == null ? "<none>" : model.getName();
            
            String name = obj.getName();
            if (name.equals("Note") || name.equals(noteType)) {
                name = "";
            }
            
            if (!name.isEmpty()) {
                symbol.append('\'', style);
                symbol.append(name, style);
                symbol.append("\' ", style);
            }
            
            symbol.append(noteType, ElementStyler.getStyler(obj, model));
            return symbol;
        }

        @objid ("34560dac-6377-4535-ba11-e329b256a924")
        @Override
        public Object visitQueryDefinition(QueryDefinition obj) {
            final StyledString symbol = new StyledString("", ElementStyler.getStyler(obj));
            if (obj.getOwnerAsCol() != null) {
                symbol.append(CoreUi.I18N.getMessage("QueryDefinition.Columns"));
            } else if (obj.getOwnerAsLine() != null) {
                symbol.append(CoreUi.I18N.getMessage("QueryDefinition.Lines"));
            } else if (obj.getOwnerAsDepth() != null) {
                symbol.append(CoreUi.I18N.getMessage("QueryDefinition.Depths"));
            } else {
                symbol.append(CoreUi.I18N.getMessage("QueryDefinition.Unknown"));
            }
            return symbol;
        }

        @objid ("81b236fa-7f24-43ee-8b4c-0df07a07193c")
        @Override
        public Object visitTagParameter(TagParameter obj) {
            final StyledString symbol = new StyledString(obj.getValue(), ElementStyler.getStyler(obj));
            return symbol;
        }

        @objid ("bfc750f1-910e-4469-8081-12b16bbaa95e")
        @Override
        public Object visitTaggedValue(TaggedValue obj) {
            final StyledString symbol = new StyledString();
            
            symbol.append("{");
            
            TagType def = obj.getDefinition();
            if (def == null) {
                symbol.append("<none>");
            } else {
                symbol.append(def.getName(), ElementStyler.getStyler(obj, def));
            }
            
            List<TagParameter> params = obj.getActual();
            if (!params.isEmpty()) {
                symbol.append(" = ");
                boolean first = true;
                for (TagParameter p : params) {
                    if (first) {
                        first = false;
                    } else {
                        symbol.append(", ");
                    }
                    symbol.append(p.getValue(), ElementStyler.getStyler(obj, p));
                }
            }
            
            symbol.append("}");
            return symbol;
        }

        /**
         * Initialize the label service.
         * 
         * @param elementStack a stack to use for recursive calls to
         * {@link #getLabel(Element, boolean)}
         */
        @objid ("a6156103-4da4-4719-9c01-e02f3d13cfc8")
        BrowserLabelService(Stack<MObject> elementStack) {
            super();
            this.elementStack = elementStack;
        }

        /**
         * Append <code>"(from xxxx)"</code> to the symbol
         * 
         * @param symbol the symbol to modify
         * @param srcObj the source object, used to compute the style of
         * <code>'xxxx'</code>
         * @param owner the object to display in <code>'xxxx'</code>
         * @param styler the style of <code>"(from "</code>
         */
        @objid ("bef6c626-ae8b-4f9b-8df8-06045c39270c")
        private void appendFrom(final StyledString symbol, ModelElement srcObj, ModelTree owner, final Styler styler) {
            if (owner != null) {
                symbol.append(" (from ", styler);
                symbol.append(owner.getName(), ElementStyler.getStyler(srcObj, owner));
                symbol.append(")", styler);
            }
        }

        @objid ("c9bfe0c7-9809-46fa-9ed3-edddd4e28655")
        private static StringBuilder getDependencyVerb(ModelElement dep, String defaultVerb) {
            final StringBuilder stringBuilder = new StringBuilder();
            if (!dep.getExtension().isEmpty()) {
                for (final Stereotype v : dep.getExtension()) {
                    stringBuilder.append(ModuleI18NService.getLabel(v));
            
                    stringBuilder.append(", ");
                }
                // remove last ", "
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            } else {
                stringBuilder.append(defaultVerb);
                /*
                 * if (dep instanceof Usage) stringBuilder.append("uses"); else
                 * if (dep instanceof ElementRealization)
                 * stringBuilder.append("realizes"); else if (dep instanceof
                 * Abstraction) stringBuilder.append("abstracts"); else
                 * stringBuilder.append("depends on");
                 */
            }
            return stringBuilder;
        }

        @objid ("a43e9a4c-b7dd-49f1-9dd9-eb48ee71eb35")
        private Object visitDependencyLikeObject(ModelElement theDependency, String mmverb, ModelElement destination) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theDependency);
            final StringBuilder verb = BrowserLabelService.getDependencyVerb(theDependency, mmverb).append(" ");
            
            if (destination != null) {
                symbol.append(verb.toString(), styler);
            
                final StyledString destLabel = new BrowserLabelService(this.elementStack).getLabel(destination);
                symbol.append(destLabel.getString(), ElementStyler.getStyler(theDependency, destination));
            
                ModelTree owner = null;
            
                if (destination instanceof ModelTree) {
                    owner = ((ModelTree) destination).getOwner();
                    appendFrom(symbol, theDependency, owner, styler);
                }
            
            } else {
                symbol.append(verb.toString(), styler);
                symbol.append("<No destination>", styler);
            }
            return symbol;
        }

        @objid ("1113479d-4b7b-4e96-bf5e-354fda4715d6")
        @Override
        public Object visitImpactLink(ImpactLink theImpactLink) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theImpactLink);
            
            ModelElement source = theImpactLink.getImpacted();
            ModelElement target = theImpactLink.getDependsOn();
            
            if (source != null) {
                symbol.append(getLabel(source).toString(), ElementStyler.getStyler(theImpactLink, source));
            }
            
            symbol.append(" -> ", styler);
            
            if (target != null) {
                symbol.append(getLabel(target).toString(), ElementStyler.getStyler(theImpactLink, target));
            }
            
            String causes = " (" + theImpactLink.getCauses().size() + " cause(s))";
            symbol.append(causes, styler);
            return symbol;
        }

    }

    @objid ("b24690c7-0a24-457f-a7b7-02b912e18740")
    private class MmServicesListener implements IRegistryEventListener {
        @objid ("2a51c816-b1fb-47e4-8c3a-dae843fecc6a")
        public MmServicesListener() {
            // Empty
        }

        @objid ("253b4ab2-16e3-4961-9f0c-e64d1a60fdcb")
        @Override
        public void added(IExtension[] extensions) {
            for (IExtension extension : extensions) {
                IConfigurationElement[] configurationElements = extension.getConfigurationElements();
                addExtensionElements(configurationElements);
            }
        }

        @objid ("556abb60-5d06-47f8-8622-8bd9eaeb6f3f")
        @Override
        public void removed(IExtension[] extensions) {
            for (IExtension extension : extensions) {
                IConfigurationElement[] configurationElements = extension.getConfigurationElements();
                removeExtensionElements(configurationElements);
            }
        }

        @objid ("45da8497-8615-41d4-ac44-429e6d106182")
        @Override
        public void added(IExtensionPoint[] extensionPoints) {
            // nothing
        }

        @objid ("b5eb0416-ffe3-42da-bc22-08e94e1150c8")
        @Override
        public void removed(IExtensionPoint[] extensionPoints) {
            // nothing
        }

    }

}
