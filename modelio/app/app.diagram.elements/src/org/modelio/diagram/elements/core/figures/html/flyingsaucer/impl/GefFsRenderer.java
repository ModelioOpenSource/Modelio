/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.diagram.elements.core.figures.html.flyingsaucer.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xhtmlrenderer.css.style.CssContext;
import org.xhtmlrenderer.css.style.derived.RectPropertySet;
import org.xhtmlrenderer.extend.FSCanvas;
import org.xhtmlrenderer.extend.NamespaceHandler;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.TextRenderer;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.extend.UserInterface;
import org.xhtmlrenderer.layout.BoxBuilder;
import org.xhtmlrenderer.layout.Layer;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.render.Box;
import org.xhtmlrenderer.render.RenderingContext;
import org.xhtmlrenderer.render.ViewportBox;
import org.xhtmlrenderer.resource.XMLResource;
import org.xhtmlrenderer.simple.NoNamespaceHandler;
import org.xhtmlrenderer.simple.extend.NoReplacedElementFactory;
import org.xhtmlrenderer.swt.NaiveUserAgent;
import org.xhtmlrenderer.swt.SWTFontResolver;
import org.xhtmlrenderer.swt.SWTReplacedElementFactory;
import org.xhtmlrenderer.util.Configuration;
import org.xhtmlrenderer.util.Uu;
import org.xhtmlrenderer.util.XRLog;
import org.xml.sax.InputSource;

/**
 * Figure that renders XML+CSS .
 * 
 * @author cmarin
 */
@objid ("d9c342c6-f94a-44af-8a8f-40988eb30516")
@SuppressWarnings("javadoc")
public class GefFsRenderer extends Figure implements UserInterface, FSCanvas {
    @objid ("329f2d1d-3943-4e2f-a133-07f93c34bcf6")
    private float _fontScalingFactor = 1.2F;

    @objid ("10de191a-65ef-4ff5-83d1-2dd7d64b8015")
    private float _maxFontScale = 3.0F;

    @objid ("a6f696f9-417f-4b4c-815e-63d69b3030f4")
    private float _minFontScale = 0.50F;

    @objid ("4c160091-adcb-4f73-9e82-babcc276e5cf")
    private Element _active_element = null;

    @objid ("bf601412-ac14-429c-9e5e-c74e6333efbc")
    private Document _doc = null;

    @objid ("482eff69-16ab-4294-a744-cf34dd90bb95")
    private Point _docOrigin = null;

    @objid ("83449743-310a-45cc-827f-7f620402cb7b")
    private Element _focus_element = null;

    @objid ("6b7ebf8b-5f05-463d-90a5-4c814ed3e8d0")
    private Element _hovered_element = null;

    @objid ("80c5ace8-d307-4023-bd5d-4763cb2f0d80")
    private Image _offscreen = null;

    @objid ("c5e54ffa-4ad2-4052-9564-df23c80851ed")
    private final Display display;

    @objid ("ac9fa87c-c432-4d3f-8b82-753481f6d7bc")
    private BlockBox _rootBox = null;

    @objid ("fcbca8bb-4634-44f7-82d1-d2105c1eaae3")
    private final SharedContext _sharedContext;

    /**
     * Construct the GefFsRenderer.
     * @param swtComposite a SWT control to allow placing SWT controls inside, not yet used.
     */
    @objid ("447bd77a-cb4a-4e2e-940f-0cc1cab7b89b")
    public  GefFsRenderer(Composite swtComposite) {
        this(swtComposite, new NaiveUserAgent(swtComposite != null ? swtComposite.getDisplay() : Display.getDefault()));
    }

    /**
     * Construct the GefFsRenderer.
     * @param swtComposite a SWT control to allow placing SWT controls inside, not yet used.
     * @param uac user agent using the panel.
     */
    @objid ("2eff6df1-39c1-4801-a2d9-7e1ab9a85446")
    public  GefFsRenderer(Composite swtComposite, UserAgentCallback uac) {
        // Make sure the display is not null, it happens with silent editors.
        this.display = swtComposite != null ? swtComposite.getDisplay() : Display.getDefault();
        this._sharedContext = new SharedContext(uac,
                new SWTFontResolver(this.display),
                new NoReplacedElementFactory(), // new SWTXhtmlReplacedElementFactory()
                new GefFsTextRenderer(),
                this.display.getDPI().y);
        
        this._sharedContext.setCanvas(this);
        
        setLayoutManager(new SfLayoutManager());
        
    }

    /**
     * Decrements all rendered fonts on the current document by the current
     * scaling factor for the panel. Scaling applies culmulatively, which means
     * that multiple calls to this method scale fonts smaller and smaller by
     * applying the current scaling factor against itself. You can modify the
     * scaling factor by {@link #setFontScalingFactor(float)}, and reset to the
     * document's specified font size with {@link #resetFontSize()}.
     */
    @objid ("8e332198-bff7-459b-bfe0-95509b53b3bf")
    public void decrementFontSize() {
        scaleFont(1 / this._fontScalingFactor);
    }

    @objid ("1f231127-94f4-4050-820b-af26ba808017")
    public Box find(int x, int y) {
        Layer l = getRootLayer();
        if (l != null) {
            return l.find(newLayoutContext(), x, y , false);
        }
        return null;
    }

    @objid ("e9e638cb-e195-4399-8957-e6e5bc12ce35")
    public Element getActive_element() {
        return this._active_element;
    }

    @objid ("f5479ea7-e900-4271-8685-f7f1b6eb76c7")
    public Document getDocument() {
        return this._doc;
    }

    @objid ("1f531a3c-2fce-4cb2-9d8a-f1e99c8ff4d2")
    @Override
    public java.awt.Rectangle getFixedRectangle() {
        Rectangle area = getClientArea();
        return new java.awt.Rectangle(0, 0, area.width(), area.height());
    }

    @objid ("38dc5278-ab02-46a7-8baf-1a38829ffb9c")
    public Element getFocus_element() {
        return this._focus_element;
    }

    @objid ("81e9f2a6-7f16-4e7f-9e81-93563243fb76")
    public Element getHovered_element() {
        return this._hovered_element;
    }

    /**
     * Returns the maximum font scaling that may be applied, e.g. 3 times
     * assigned font size.
     */
    @objid ("6bba7fab-86d9-49e5-b022-2b6f5448310d")
    public float getMaxFontScale() {
        return this._maxFontScale;
    }

    /**
     * Returns the minimum font scaling that may be applied, e.g. 0.5 times
     * assigned font size.
     */
    @objid ("f0d0a562-0c12-4f51-b677-2ccac98ad898")
    public float getMinFontScale() {
        return this._minFontScale;
    }

    @objid ("544980df-0dcb-4f8d-8bd5-7f5295e8c4b5")
    public Box getRootBox() {
        return this._rootBox;
    }

    @objid ("07802a30-9375-41ac-9a34-11ce4f83fb62")
    public Layer getRootLayer() {
        return getRootBox() == null ? null : getRootBox().getLayer();
    }

    @objid ("e30c2232-d471-422b-901d-7c966169449f")
    public SharedContext getSharedContext() {
        return this._sharedContext;
    }

    @objid ("4d34037d-c246-4860-a82d-86f54fa97216")
    @Override
    public int getX() {
        return  - getDocumentOrigin().x;
    }

    @objid ("04f6da69-cac0-42ea-a633-8c5288db5301")
    @Override
    public int getY() {
        return - getDocumentOrigin().y;
    }

    /**
     * Increments all rendered fonts on the current document by the current
     * scaling factor for the panel. Scaling applies culmulatively, which means
     * that multiple calls to this method scale fonts larger and larger by
     * applying the current scaling factor against itself. You can modify the
     * scaling factor by {@link #setFontScalingFactor(float)}, and reset to the
     * document's specified font size with {@link #resetFontSize()}.
     */
    @objid ("c4dc10a4-9b37-4853-86d7-befdbac277a8")
    public void incrementFontSize() {
        scaleFont(this._fontScalingFactor);
    }

    @objid ("dfbfa19f-694a-4be4-be78-54b0d269b2c5")
    @Override
    public void invalidate() {
        if (this._offscreen != null) {
            this._offscreen.dispose();
            this._offscreen = null;
        }
        
        this._docOrigin = null;
        
        super.invalidate();
        
    }

    @objid ("4b9cb983-d992-4412-b413-7b5fa09017f0")
    @Override
    public boolean isActive(org.w3c.dom.Element e) {
        if (e == this._active_element) {
            return true;
        }
        return false;
    }

    @objid ("98b3726b-332a-4265-b7c2-dfc1bd80bb9f")
    @Override
    public boolean isFocus(org.w3c.dom.Element e) {
        if (e == this._focus_element) {
            return true;
        }
        return false;
    }

    @objid ("0fe75d34-7e61-4acc-bc8f-3ea82cbbd410")
    @Override
    public boolean isHover(org.w3c.dom.Element e) {
        if (e == this._hovered_element) {
            return true;
        }
        return false;
    }

    @objid ("9c21df31-fc1c-4925-b9aa-8b4c69d5a964")
    public boolean isPrint() {
        return this._sharedContext.isPrint();
    }

    /**
     * Reload the current document.
     */
    @objid ("68e59b15-96c7-446a-afc1-f2f7ada714a8")
    public void reload() {
        if (this._doc == null) {
            return;
        }
        this._rootBox = null;
        this._active_element = null;
        this._hovered_element = null;
        this._focus_element = null;
        if (Configuration.isTrue("xr.cache.stylesheets", true)) {
            this._sharedContext.getCss().flushStyleSheets();
        } else {
            this._sharedContext.getCss().flushAllStyleSheets();
        }
        
        setCursor(null);
        this._sharedContext.reset();
        if (this._offscreen != null) {
            this._offscreen.dispose();
            this._offscreen = null;
        }
        
        revalidate();
        repaint();
        
    }

    /**
     * Resets all rendered fonts on the current document to the font size
     * specified in the document's styling instructions.
     */
    @objid ("c3c780b1-26dd-49b0-af79-bf5cfb8f6ea7")
    public void resetFontSize() {
        getSharedContext().getTextRenderer().setFontScale(1f);
        reload();
        
    }

    @objid ("52aa77e2-b890-4b3a-b999-cf77b0b6a842")
    public void setActive_element(Element active_element) {
        this._active_element = active_element;
    }

    @objid ("910a1190-8087-4063-b475-bffbc2259618")
    public void setDocument(Document doc, URL url, NamespaceHandler nsh) {
        this._rootBox = null;
        this._doc = doc;
        
        this._active_element = null;
        this._hovered_element = null;
        this._focus_element = null;
        
        // have to do this first
        if (Configuration.isTrue("xr.cache.stylesheets", true)) {
            this._sharedContext.getCss().flushStyleSheets();
        } else {
            this._sharedContext.getCss().flushAllStyleSheets();
        }
        
        setCursor(null);
        this._sharedContext.reset();
        if (this._offscreen != null) {
            this._offscreen.dispose();
            this._offscreen = null;
        }
        
        if (doc == null) {
            //this._drawnSize = new Point(1, 1);
        } else {
            this._sharedContext.setBaseURL((url == null) ? null : url.toString());
            this._sharedContext.setNamespaceHandler(nsh);
            this._sharedContext.getCss().setDocumentContext(this._sharedContext,
                    this._sharedContext.getNamespaceHandler(), doc, this);
        }
        
        revalidate();
        repaint();
        
    }

    @objid ("1d2ec814-bec5-4f36-a340-aee4a3a5daa6")
    public void setDocument(InputStream stream, URL url, NamespaceHandler nsh) {
        Document dom = XMLResource.load(stream).getDocument();
        
        setDocument(dom, url, nsh);
        
    }

    @objid ("c3e2d1bd-d547-43fa-8e40-0bdbc283a1c6")
    public void setDocument(Document doc, URL url) {
        setDocument(doc, url, new NoNamespaceHandler());
    }

    @objid ("05ef6493-40f2-45a0-9713-2e08a3a12a41")
    public void setDocument(URL url) {
        setDocument(loadDocument(url), url, new NoNamespaceHandler());
    }

    @objid ("8f400b0a-be18-4dbb-a42c-82de5c803650")
    public void setDocument(URL url, NamespaceHandler nsh) {
        setDocument(loadDocument(url), url, nsh);
    }

    @objid ("acc4403d-986e-4113-b0d7-c621635dd4b3")
    public void setDocument(InputStream stream, URL url) {
        setDocument(stream, url, new NoNamespaceHandler());
    }

    @objid ("9a893994-b15e-48f4-9503-e11ea64eef4c")
    public void setDocumentFromString(String content, URL url, NamespaceHandler nsh) {
        InputSource is = new InputSource(new BufferedReader(new StringReader(content)));
        Document dom = XMLResource.load(is).getDocument();
        
        setDocument(dom, url, nsh);
        
    }

    @objid ("add8bbc3-9b83-42d4-8031-45f9e814ad11")
    public void setFocus_element(Element focus_element) {
        this._focus_element = focus_element;
    }

    /**
     * Sets the scaling factor used by {@link #incrementFontSize()} and
     * {@link #decrementFontSize()}--both scale the font up or down by this
     * scaling factor. The scaling roughly modifies the font size as a
     * multiplier or divisor. A scaling factor of 1.2 applied against a font
     * size of 10pt results in a scaled font of 12pt. The default scaling factor
     * is 1.2F.
     */
    @objid ("df95155a-1076-4647-90b8-742813a921fc")
    public void setFontScalingFactor(float scaling) {
        this._fontScalingFactor = scaling;
    }

    @objid ("bbaa2232-ce69-4c80-9e4b-da5da8e7a7b6")
    public void setHovered_element(Element hovered_element) {
        this._hovered_element = hovered_element;
    }

    /**
     * Only our layout manager is allowed.
     * @param manager our SfLayoutManager.
     */
    @objid ("09499821-8e1d-4597-b6b4-44059d7faf81")
    @Override
    public final void setLayoutManager(LayoutManager manager) {
        assert (manager instanceof SfLayoutManager);
        
        super.setLayoutManager(manager);
        
    }

    /**
     * Sets the maximum font scaling that may be applied, e.g. 3 times assigned
     * font size. Calling incrementFontSize() after this scale has been reached
     * doesn't have an effect.
     */
    @objid ("c054b1cb-c093-4fb6-abe6-cc86553131db")
    public void setMaxFontScale(float f) {
        this._maxFontScale = f;
    }

    /**
     * Sets the minimum font scaling that may be applied, e.g. 3 times assigned
     * font size. Calling decrementFontSize() after this scale has been reached
     * doesn't have an effect.
     */
    @objid ("0e1a5551-c87d-498c-8e27-6ce770edbcff")
    public void setMinFontScale(float f) {
        this._minFontScale = f;
    }

    @objid ("1ead1c55-e826-4eb2-82d3-d9f3a92654fd")
    public void setPrint(boolean print) {
        this._sharedContext.setPrint(print);
        this._sharedContext.setInteractive(!print);
        this._sharedContext.getReplacedElementFactory().reset();
        reload();
        
    }

    /**
     * Calculates the preferred size of the figure, using width and height hints.
     * <p>
     * Called by the figure layout manager.
     * @param wHint The width hint
     * @param hHint The height hint
     * @return the preferred size
     */
    @objid ("77fda852-fb28-42c2-8fc6-d4b3a4807e4f")
    Dimension calculatePreferredSize(int wHint, int hHint) {
        // Sometimes, this._doc happens to be null...
        if (this._doc == null) {
            return new Dimension(getInsets().getWidth(), getInsets().getHeight());
        }
        
        try {
            long start = System.currentTimeMillis();
            LayoutContext layout_context = newLayoutContext();
        
            if (this._rootBox != null) {
                this._rootBox.reset(layout_context);
            } else {
                this._rootBox = BoxBuilder.createRootBox(layout_context, this._doc);
            }
        
            java.awt.Rectangle awtRect = new java.awt.Rectangle(wHint, hHint);
            if (wHint > 0 )
                awtRect.width -= getInsets().getWidth();
        
            if (hHint > 0)
                awtRect.height -= getInsets().getHeight();
        
        
            this._rootBox.setContainingBlock(new ViewportBox(awtRect));
            this._rootBox.layout(layout_context);
        
            long end = System.currentTimeMillis();
            XRLog.layout(Level.INFO, "calculatePreferredSize(...) took " + (end - start) + "ms");
        
            Layer rootLayer = this._rootBox.getLayer();
            java.awt.Dimension pref_size = rootLayer.getPaintingDimension(layout_context);
        
            return new PrecisionDimension(pref_size.getWidth(), pref_size.getHeight())
                    .expand(getInsets().getWidth(), getInsets().getHeight());
        
        } catch (Exception e) {
            DiagramElements.LOG.debug(e);
            return new Dimension(100, 100);
        }
        
    }

    /**
     * Layout the HTML content.
     * <p>
     * Called by the custom layout manager.
     */
    @objid ("ce2278cd-14db-4f88-91ed-8c1fdf87bf22")
    void computeLayout() {
        if (this._doc == null) {
            return;
        }
        
        LayoutContext layout_context = newLayoutContext();
        
        try {
            long start = System.currentTimeMillis();
        
            if (this._rootBox != null ) {
                this._rootBox.reset(layout_context);
            } else {
                this._rootBox = BoxBuilder.createRootBox(layout_context, this._doc);
            }
        
            Rectangle size = getClientArea();
            if (size.width() == 0 && size.height() == 0) {
                size.width = 1;
                size.height = 1;
            }
            java.awt.Rectangle awtRect = new java.awt.Rectangle(size.width(), size.height());
            this._rootBox.setContainingBlock(new ViewportBox(awtRect));
        
            this._rootBox.layout(layout_context);
            this._docOrigin = calcDocumentOrigin(layout_context);
        
            long end = System.currentTimeMillis();
            //DiagramElements.LOG.debug("%s: Layout took %d ms.", getClass().getSimpleName(), end - start);
        } catch (Exception e) {
            DiagramElements.LOG.warning(getClass().getSimpleName()+":  "+e.toString());
            DiagramElements.LOG.debug(e);
        }
        
    }

    @objid ("25d1c221-6a68-4b60-b0a4-6bba6a2163e2")
    protected Point calcDocumentOrigin(CssContext layout_context) {
        Box box = getRootBox();
        if (box == null)
            return new Point(0,0);
        
        if (box.getStyle().isInline()) {
            return new Point(box.getAbsX() , box.getAbsY());
        } else {
            RectPropertySet margin = box.getMargin(layout_context);
            return new Point(
                    box.getAbsX() + (int) margin.left() ,
                    box.getAbsY() + (int) margin.top());
        }
        
    }

    @objid ("36a36ac7-2c1d-4ab7-8795-428c7432bb6d")
    protected void doRender(RenderingContext c) {
        try {
            long start = System.currentTimeMillis();
            //this._sharedContext.setDebug_draw_boxes(false);
            this._rootBox.getLayer().paint(c);
        
            long after = System.currentTimeMillis();
            if (Configuration.isTrue("xr.incremental.repaint.print-timing", false)) {
                Uu.p("repaint took ms: " + (after - start));
            }
        } catch (Exception e) {
            DiagramElements.LOG.warning(getClass().getSimpleName()+":  "+e.toString());
            DiagramElements.LOG.debug(e);
        }
        ((GefFsOutputDevice) c.getOutputDevice()).clean();
        
    }

    @objid ("26080035-3b40-485c-af5f-a960f078d627")
    @Override
    protected void finalize() {
        // dispose used fonts
        this._sharedContext.flushFonts();
        
        // clean ReplacedElementFactory
        ReplacedElementFactory ref = this._sharedContext.getReplacedElementFactory();
        if (ref instanceof SWTReplacedElementFactory) {
            ((SWTReplacedElementFactory) ref).clean();
        }
        // dispose images when using NaiveUserAgent
        UserAgentCallback uac = this._sharedContext.getUac();
        if (uac instanceof NaiveUserAgent) {
            ((NaiveUserAgent) uac).disposeCache();
        }
        
        // dispose offscreen image
        if (this._offscreen != null) {
            this._offscreen.dispose();
            this._offscreen = null;
        }
        
    }

    @objid ("771f69c7-1aca-4eba-91df-3be3631c4abd")
    protected final Device getDisplay() {
        return this.display;
    }

    @objid ("c02cb088-cbc2-46aa-83fe-d9e98dbe669b")
    protected final Point getDocumentOrigin() {
        if (this._docOrigin == null)
            this._docOrigin = calcDocumentOrigin(newLayoutContext());
        return this._docOrigin;
    }

    @objid ("dc391eeb-870b-4770-becb-b3ff8cfbe27c")
    protected Document loadDocument(final URL uri) {
        XMLResource xmlResource = this._sharedContext.getUac().getXMLResource(uri.toString());
        if (xmlResource == null) {
            return null;
        }
        return xmlResource.getDocument();
    }

    /**
     * @return a new {@link LayoutContext}
     */
    @objid ("feb47d96-65c0-4deb-958a-b46190528ee8")
    protected LayoutContext newLayoutContext() {
        LayoutContext result = this._sharedContext.newLayoutContextInstance();
        
        result.setFontContext(new GefFsFontContext());
        this._sharedContext.getTextRenderer().setup(result.getFontContext());
        return result;
    }

    /**
     * @param gc the draw2d GC
     * @return a new {@link RenderingContext}
     */
    @objid ("f1ebe230-0a61-4422-9068-daf514dee18d")
    protected RenderingContext newRenderingContext(Graphics graphics) {
        RenderingContext result = this._sharedContext.newRenderingContextInstance();
        
        result.setFontContext(new GefFsFontContext());
        result.setOutputDevice(new GefFsOutputDevice(graphics, this.display));
        
        this._sharedContext.getTextRenderer().setup(result.getFontContext());
        return result;
    }

    @objid ("d45d029b-f292-426b-8ccc-b064d3b8b4ab")
    @Override
    protected void paintFigure(Graphics graphics) {
        if (this._doc == null) {
            // just draw background
            graphics.fillRectangle(getClientArea());
        }
        
        // if this is the first time painting this document, then calc layout
        Layer root = getRootLayer();
        if (root == null) {
            revalidate();
            root = getRootLayer();
            if (this._offscreen != null) {
                // invalidate offscreen image
                this._offscreen.dispose();
                this._offscreen = null;
            }
        }
        
        if (root == null) {
            XRLog.render(Level.FINE, "skipping the actual painting");
        } else {
            //if (! USE_OFFSET_IMAGE) {
            // Render directly to display
            RenderingContext c = newRenderingContext(graphics);
            graphics.translate(getClientArea().getLocation());
            graphics.translate(getDocumentOrigin().negate());
        
            doRender(c);
        
            /*} else {
                // Use an off-screen image
                Rectangle size = getClientArea();
        
                if (this._offscreen != null) {
                    // Check whether off-screen image is big enough
                    org.eclipse.swt.graphics.Rectangle offBounds = this._offscreen.getBounds();
        
                    if (offBounds.width < size.width() || offBounds.height < size.height()) {
                        if (this._offscreen != null) { // full redraw
                            this._offscreen.dispose();
                            this._offscreen = null;
                        }
                    }
                }
        
                // remake off-screen if needed
                if (this._offscreen == null) {
        
                    // initialize Image, SWT GC, draw2d Graphics and HTML renderer
                    this._offscreen = createTransparentImage(size);
                    GC gc = new GC(this._offscreen);
                    SWTGraphics gefGc = new SWTGraphics(gc);
                    RenderingContext c = newRenderingContext(gefGc);
        
                    doRender(c);
        
                    gefGc.dispose();
                    gc.dispose();
                }
        
                // draw on screen
                graphics.drawImage(this._offscreen, getClientArea().getLocation());
            }*/
        }
        
    }

    /**
     * Sets the new current document, where the new document is located
     * relative, e.g using a relative URL.
     * @throws MalformedURLException
     * @param filename The new document to load
     */
    @objid ("621f42a6-c71f-4dfc-9dd7-47ac612a8ad9")
    protected void setDocumentRelative(String filename) throws MalformedURLException {
        String surl = this._sharedContext.getUac().resolveURI(filename);
        if (isAnchorInCurrentDocument(filename)) {
            String id = getAnchorId(filename);
            Box box = this._sharedContext.getBoxById(id);
            if (box != null) {
                /*Point pt;
                if (box.getStyle().isInline()) {
                    pt = new Point(0 , box.getAbsY());
                } else {
                    RectPropertySet margin = box.getMargin(this._layout_context);
                    pt = new Point(0 , box.getAbsY()
                            + (int) margin.top());
                }
                setOrigin(pt);*/
                invalidate();
                return;
            }
        }
        
        final URL url = new URL(surl);
        Document dom = loadDocument(url);
        setDocument(dom, url);
        
    }

    @objid ("59885e2e-b896-4d78-acd3-e8158876f80f")
    private String getAnchorId(String url) {
        return url.substring(1, url.length());
    }

    @objid ("32e60732-ae4c-431c-ad33-9a809947deec")
    private boolean isAnchorInCurrentDocument(String str) {
        return str.charAt(0) == '#';
    }

    /**
     * Applies a change in scale for fonts using the rendering context's text
     * renderer.
     */
    @objid ("d8079c9a-26e0-499d-8643-b1fd2f4e5d70")
    private void scaleFont(float scaleBy) {
        TextRenderer tr = getSharedContext().getTextRenderer();
        float fs = tr.getFontScale() * scaleBy;
        if (fs < this._minFontScale || fs > this._maxFontScale) {
            return;
        }
        tr.setFontScale(fs);
        reload();
        
    }

    /**
     * Layout manager calling {@link GefFsRenderer#calculatePreferredSize(int, int)}
     * and {@link GefFsRenderer#computeLayout()}.
     */
    @objid ("f5f3c0c0-9a95-4f6c-87fb-4f5c7d9855d1")
    class SfLayoutManager extends AbstractHintLayout {
        @objid ("48384f4c-d96c-4976-9c5a-7c1bcdd1a13e")
        @Override
        public void layout(IFigure container) {
            GefFsRenderer.this.computeLayout();
        }

        @objid ("fcde842a-8a30-4c12-bcb5-6687b901196a")
        @Override
        protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
            return GefFsRenderer.this.calculatePreferredSize(wHint, hHint);
        }

    }

}
