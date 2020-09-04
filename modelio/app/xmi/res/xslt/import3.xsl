<?xml version="1.0"?>
<xsl:stylesheet version="1.0" 
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
		xmlns:xmi="http://schema.omg.org/spec/XMI/2.1"  
		xmlns:uml="http://www.eclipse.org/uml2/2.1.0/UML"  
		xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" 
		xmlns:sysML="http://www.topcased.org/2.0/sysML"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
        
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<!-- Reproduit tous les noeuds du document  -->

<xsl:template match="/">
		<xsl:apply-templates />
</xsl:template>


<xsl:template match=" @*| * | text() ">	
	<xsl:copy>
		<xsl:apply-templates select="@* | * | text()"/>
	</xsl:copy>	
</xsl:template> 

<!--<xsl:template match="//type[not(@xmi:type)][contains(@href,'UMLPrimitiveTypes.library.uml')]"> 
	<xsl:copy>
	<xsl:apply-templates select="@* | * | text()"/>
    <xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:PrimitiveType</xsl:attribute>
   </xsl:copy>
</xsl:template> -->


<xsl:template match="//generalization[./general[@href]]">
<xsl:copy> 
  <xsl:variable name= "general">
  <xsl:for-each select="./general/@href">
    <xsl:value-of select="."/>
  </xsl:for-each>
  </xsl:variable>
  <xsl:attribute name="general" namespace=""><xsl:value-of select="$general"/></xsl:attribute> 
  <xsl:apply-templates select="@* | * | text()"/> 
  </xsl:copy>
</xsl:template> 

<xsl:template match="//generalization/general[@href]"/>

<xsl:template match="//*/memberEnd[@xmi:idref]"/> 


<xsl:template match="//*/incoming[@xmi:idref]"/> 


<xsl:template match="//*/outgoing[@xmi:idref]"/>


<xsl:template match="//*/covered[@xmi:idref]"/> 


<xsl:template match="//*/type[@xmi:idref]"/> 


<xsl:template match="//packagedElement[@xmi:type='uml:Stereotype']">

	<xsl:copy>
	<xsl:apply-templates select="@* | * | text()"/>
		<xsl:variable name = 'sterName'>
			<xsl:choose>

			<xsl:when test="contains(./@xmi:id,' ')">
 				<xsl:value-of select="translate(./@xmi:id,' ','_')"/>  
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="./@xmi:id"/>
			</xsl:otherwise>  
 			</xsl:choose>  
 		</xsl:variable>

	</xsl:copy>

		
</xsl:template> 


<xsl:template match="//uml:Model//*[@xmi:id][@xmi:type]">
	
	<xsl:copy>
	<xsl:apply-templates select="@* | * | text()"/>
	<xsl:variable name ="baseID" ><xsl:value-of select="@xmi:id"/></xsl:variable>
	<xsl:variable name ="attrbaseName" ><xsl:value-of select="concat('base_',substring-after(@xmi:type,':'))"/></xsl:variable>
	<xsl:for-each select="//@*[name()= $attrbaseName][. = $baseID]">
		  <xsl:variable name ="nodeName"><xsl:value-of select="name(..)"/></xsl:variable>
		  <xsl:variable name ="profile" ><xsl:value-of select="substring-before($nodeName,':')"/></xsl:variable>
		  
		  <xsl:variable name ="sterName" >
		  <xsl:choose>
		    <xsl:when  test="../@__EAStereoName">
		    <xsl:value-of select="../@__EAStereoName"/>
		    </xsl:when>
		    <xsl:otherwise>
		    
		    <xsl:value-of select="substring-after($nodeName,':')"/>
        </xsl:otherwise>
		  </xsl:choose>
		  </xsl:variable>
		  <xsl:value-of select="$sterName"/>
		  
		   <xsl:variable name ="ster" ><xsl:value-of select="substring-after($nodeName,':')"/></xsl:variable>
		  
		   <xsl:variable name ="newNodeName" ><xsl:value-of select="concat($profile,':',$sterName)"/></xsl:variable>
		  	<xsl:for-each select="//packagedElement[@xmi:type='uml:Profile'][@name=$profile]//packagedElement[@xmi:type='uml:Stereotype'][@name=$sterName]">
		  	
		  		<xsl:variable name = 'attrList'><xsl:value-of select="./ownedAttribute[not(@association)]"/></xsl:variable>
	  			<xsl:call-template name="createAnnotation">
							<xsl:with-param name ="sterName" ><xsl:value-of select="$newNodeName"/></xsl:with-param>
							<xsl:with-param name ="attrbaseName" ><xsl:value-of select="$attrbaseName"/></xsl:with-param>
							<xsl:with-param name ="baseID" ><xsl:value-of select="$baseID"/></xsl:with-param>
				</xsl:call-template>
		  
		  	</xsl:for-each>
	</xsl:for-each>
	
</xsl:copy>

</xsl:template>


<xsl:template name="createAnnotation">

	<xsl:param name="sterName"/>
	<xsl:param name="attrbaseName"/>
	<xsl:param name="baseID"/>

	<xsl:element name="eAnnotations" >
					<xsl:attribute name="xmi:id" namespace="http://schema.omg.org/spec/XMI/2.1"><xsl:value-of select="generate-id()"/></xsl:attribute>
					<xsl:attribute name="source">Objing</xsl:attribute>
					<xsl:call-template name="contents">
						<xsl:with-param name ="name" >Stereotype</xsl:with-param>
						<xsl:with-param name ="value" ><xsl:value-of select="$sterName"/></xsl:with-param>
						</xsl:call-template>
					
						<xsl:for-each select="//*[name() = $sterName]/@*[name() = $attrbaseName]">
							
							<xsl:if test="(. =  $baseID)">
							
							<xsl:for-each select="../@*[name() != $attrbaseName][name() != 'xmi:id']">
							
								<xsl:variable name = 'name'><xsl:value-of select="name()"/></xsl:variable>
								<xsl:variable name = 'value'><xsl:value-of select="."/></xsl:variable>
									
									<xsl:call-template name="contents">
										<xsl:with-param name ="name" ><xsl:value-of select="$name"/></xsl:with-param>
										<xsl:with-param name ="value" ><xsl:value-of select="$value"/></xsl:with-param>
									</xsl:call-template>
								
						
							</xsl:for-each >
							</xsl:if>
						</xsl:for-each >
					
				</xsl:element>		

	
</xsl:template>



<xsl:template name="contents">
	<xsl:param name="name"/>
	<xsl:param name="value"/>
	
	<xsl:element name="contents" >
	
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Property</xsl:attribute>
		<xsl:attribute name="xmi:id" namespace="http://schema.omg.org/spec/XMI/2.1"><xsl:value-of select="generate-id()"/></xsl:attribute>
		<xsl:attribute name="name" namespace=""><xsl:value-of select="$name"/></xsl:attribute>
		<xsl:call-template name="defaultValue">
			<xsl:with-param name ="value" ><xsl:value-of select="$value"/></xsl:with-param>
		</xsl:call-template>
					
	</xsl:element>
	
</xsl:template>

<xsl:template name="defaultValue">
	<xsl:param name="value"/>
	<xsl:element name="defaultValue" >
	
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:LiteralString</xsl:attribute>
		<xsl:attribute name="xmi:id" namespace="http://schema.omg.org/spec/XMI/2.1"><xsl:value-of select="generate-id()"/></xsl:attribute>
		<xsl:attribute name="value" namespace=""><xsl:value-of select="$value"/></xsl:attribute>
					
	</xsl:element>
</xsl:template>



<xsl:template match="//packagedElement[@xmi:type='uml:Profile']//*/@xmi:id">

	<xsl:choose>

	<xsl:when test="contains(.,' ')">
		<xsl:attribute name="xmi:id" namespace="http://schema.omg.org/spec/XMI/2.1">  
 			<xsl:value-of select="translate(.,' ','_')"/>  
		</xsl:attribute>
	</xsl:when>
	
	<xsl:otherwise>
		<xsl:copy-of select="."/>
	</xsl:otherwise>  
 	</xsl:choose>  
		
</xsl:template>

<xsl:template match="packageImport[@xmi:id='mmref01']"/>


<xsl:template match="//packagedElement[@xmi:type='uml:Profile']/packagedElement[@xmi:type='uml:Stereotype']/ownedAttribute/@association">
    
  <xsl:choose>

	<xsl:when test="contains(.,' ')">
		<xsl:attribute name="association" namespace="">  
 			<xsl:value-of select="translate(.,' ','_')"/>  
		</xsl:attribute>
	</xsl:when>
	
	<xsl:otherwise>
		<xsl:copy-of select="."/>
	</xsl:otherwise>
	  
 	</xsl:choose> 
	
</xsl:template>

<!-- Supress whitespace -->
<xsl:template match="//packagedElement[@xmi:type='uml:Profile']/packagedElement[@xmi:type='uml:Extension']/ownedEnd/@type">
    
  <xsl:choose>

	<xsl:when test="contains(.,' ')">
		<xsl:attribute name="type" namespace="">  
 			<xsl:value-of select="translate(.,' ','_')"/>  
		</xsl:attribute>
	</xsl:when>
	
	<xsl:otherwise>
		<xsl:copy-of select="."/>
	</xsl:otherwise> 
  
  </xsl:choose>
  		
</xsl:template>


<xsl:template match="//packagedElement[@xmi:type='uml:Profile']/packagedElement[@xmi:type='uml:Extension']/@memberEnd">
	<xsl:choose>

	<xsl:when test="contains(substring-after(.,' '),' ')">

		<xsl:variable name = "under">_</xsl:variable> 
 		<xsl:variable name = "space"><xsl:value-of select="' '"/></xsl:variable> 
 		
		<xsl:attribute name="memberEnd" namespace=""> 
	
			<xsl:call-template name="string-replace-all-first-and-last">
          	<xsl:with-param name="string" select="." />
      			<xsl:with-param name="replace" select="$space"/>
      			<xsl:with-param name="by" select="$under"/>
        	</xsl:call-template>
 			
		</xsl:attribute>
	</xsl:when>
	
	<xsl:otherwise>
		<xsl:copy-of select="."/>
	</xsl:otherwise> 
  
  	</xsl:choose>	
</xsl:template>


<xsl:template match="//packagedElement[@xmi:type='uml:Profile']//ownedAttribute[@association]/type">

	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Class</xsl:attribute>
		<xsl:apply-templates select="@* | * | text()"/>
	</xsl:copy>
	
</xsl:template> 


<xsl:template match="//packagedElement[@xmi:type='uml:Profile']//ownedAttribute[not(@association)]/type">
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:PrimitiveType</xsl:attribute>
		<xsl:apply-templates select="@* | * | text()"/>
	</xsl:copy>

</xsl:template> 


<xsl:template match="//packagedElement[@xmi:type='uml:Profile']/packagedElement[@xmi:type='uml:Extension']/ownedEnd">
	
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:ExtensionEnd</xsl:attribute>
		<xsl:apply-templates select="@* | * | text()"/>
	</xsl:copy>
	
	
	
</xsl:template>


<xsl:template match="//packagedElement[@xmi:type='uml:Extension']//ownedEnd/@memberEnd"/>



<xsl:template match="//packagedElement[@xmi:type='uml:Profile']//ownedAttribute[@association]/type/@href"> 
	<xsl:attribute name="href" namespace="">
	
	<xsl:choose>
	<xsl:when test="contains(.,'http://schema.omg.org/spec/UML/2.1/')">
		<xsl:text>pathmap://UML_METAMODELS/UML.metamodel.uml</xsl:text>
		<xsl:value-of select="substring-after(.,'http://schema.omg.org/spec/UML/2.1/')"/>
	</xsl:when> 
	
	<xsl:otherwise> <xsl:value-of select="."/>  </xsl:otherwise>
	</xsl:choose>
	</xsl:attribute> 
</xsl:template>




<xsl:template match="//packagedElement[@xmi:type='uml:Profile']//ownedAttribute[not(@association)]/type/@href"> 
	<xsl:attribute name="href" namespace="">
	
	<xsl:choose>
		<xsl:when test="contains(.,'http://schema.omg.org/spec/UML/2.1/')">
			<xsl:text>pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml</xsl:text>
			<xsl:value-of select="substring-after(.,'http://schema.omg.org/spec/UML/2.1/')"/>
		</xsl:when> 
		<xsl:when test="contains(.,'http://www.eclipse.org/uml2/2.1.0/UML/')">
			<xsl:text>pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml</xsl:text>
			<xsl:value-of select="substring-after(.,'http://www.eclipse.org/uml2/2.1.0/UML/')"/>
		</xsl:when> 
		<xsl:otherwise> 
			<xsl:value-of select="."/>  
		</xsl:otherwise>
	</xsl:choose>
	
	</xsl:attribute> 
	
</xsl:template>


<!-- Utilitaires pour la mnipultion des chaines de caractères -->


<xsl:template name="string-replace-all">
    <xsl:param name="text" />
    <xsl:param name="replace" />
    <xsl:param name="by" />
    
    <xsl:choose>
    
      <xsl:when test="contains($text, $replace)">
        <xsl:value-of select="substring-before($text,$replace)" />
        <xsl:value-of select="$by" />
        <xsl:call-template name="string-replace-all">
          <xsl:with-param name="text" select="substring-after($text,$replace)" />
          <xsl:with-param name="replace" select="$replace" />
          <xsl:with-param name="by" select="$by" />
        </xsl:call-template>
      </xsl:when>
      
      <xsl:otherwise>
        <xsl:value-of select="$text" />
      </xsl:otherwise>
      
    </xsl:choose>
    
</xsl:template>

<xsl:template match="//@__EAStereoName"/>
  
<xsl:template name="substring-before-last">
    <xsl:param name="string" />
    <xsl:param name="limit" />
    <xsl:param name="rest" />
   
   <xsl:variable name="count"> 
   		<xsl:call-template name="substring-count">
          <xsl:with-param name="string" select="$string" />
          <xsl:with-param name="substr" select="$limit" />
        </xsl:call-template>
    </xsl:variable>
   
   
    <xsl:choose>
    
    	<xsl:when test="$count > 1" >
   				<xsl:call-template name="substring-before-last">
          			<xsl:with-param name="string" select="substring-after($string,$limit)" />
          			<xsl:with-param name="limit" select="$limit" />
          			<xsl:with-param name="rest" select="concat($rest,substring-before($string,$limit),$limit)" />
        		</xsl:call-template>
    	</xsl:when>
    	
    	<xsl:otherwise>
    		<xsl:value-of select="concat($rest,substring-before($string,$limit))" />
    	</xsl:otherwise>
    		
    </xsl:choose>
    
</xsl:template>  


<xsl:template name="substring-after-last">

    <xsl:param name="string" />
    <xsl:param name="limit" />

    <xsl:choose>
    
    	<xsl:when test="contains($string,$limit)" >
    	  
   			<xsl:call-template name="substring-after-last">
          		<xsl:with-param name="string" select="substring-after($string,$limit)" />
          		<xsl:with-param name="limit" select="$limit" />
        	</xsl:call-template>
       
    	</xsl:when>
    	
    	<xsl:otherwise>
    		<xsl:value-of select="$string"/>
    	</xsl:otherwise>
    	
    </xsl:choose>
    
</xsl:template> 


<xsl:template name="substring-count">
  <xsl:param name="string"/>
  <xsl:param name="substr"/>
  <xsl:param name="rest">0</xsl:param>
  <xsl:choose>
   
    	<xsl:when test="contains($string, $substr) and $string and $substr">
      
        <xsl:call-template name="substring-count">
          <xsl:with-param name="string" select="substring-after($string, $substr)"/>
          <xsl:with-param name="substr" select="$substr"/>
          <xsl:with-param name="rest" select="$rest + 1"/>
        </xsl:call-template>
      
    </xsl:when>
    	<xsl:otherwise>
    		<xsl:value-of select="$rest"/>
    	</xsl:otherwise>
    </xsl:choose>
    
    
</xsl:template>  
  
  
<xsl:template name="string-replace-all-first-and-last">
    <xsl:param name="string" />
    <xsl:param name="replace" />
    <xsl:param name="by" />
     
    <xsl:variable name="count"> 
   		<xsl:call-template name="substring-count">
          <xsl:with-param name="string" select="$string" />
          <xsl:with-param name="substr" select="$replace" />
        </xsl:call-template>
    </xsl:variable>

    <xsl:choose>
    	<xsl:when test="$count > 1" >
    	    <xsl:variable name="before-last"> 
   				<xsl:call-template name="substring-before-last">
         			<xsl:with-param name="string" select="substring-after($string,$replace)" />
          			<xsl:with-param name="limit" select="$replace" />
        		</xsl:call-template>
   			</xsl:variable>

    		<xsl:variable name="after-last"> 
   				<xsl:call-template name="substring-after-last">
         			<xsl:with-param name="string" select="$string" />
          			<xsl:with-param name="limit" select="$replace" />
        		</xsl:call-template>
   			</xsl:variable>
    	    
    	    
   				<xsl:call-template name="string-replace-all-first-and-last">
          			<xsl:with-param name="string" select="concat(substring-before($string,$replace),$by,$before-last,$by,$after-last)" />
          			<xsl:with-param name="replace" select="$replace" />
          			<xsl:with-param name="by" select="$by" />
        		</xsl:call-template>
        	
        	
    	</xsl:when>
    	
    	<xsl:otherwise>
    		<xsl:value-of select="$string"/>
    	</xsl:otherwise>
    	
    </xsl:choose> 
    
    
</xsl:template>


</xsl:stylesheet>