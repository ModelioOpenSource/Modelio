<?xml version="1.0"?>
<xsl:stylesheet version="1.0" 
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
		xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" 
		xmlns:uml="http://www.eclipse.org/uml2/2.1.0/UML"  
		xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" 
		xmlns:sysML="http://www.topcased.org/2.0/sysML"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
		
        
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/"> 
		<xsl:apply-templates />
</xsl:template>

<!-- Reassign the library path -->
<xsl:template match="//@href"> 
	<xsl:attribute name="href" namespace="">
		<xsl:choose>
	   	<xsl:when test="contains(.,'http://schema.omg.org/spec/UML/2.1/uml.xml')">
		  	<xsl:text>pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml</xsl:text>
		  	<xsl:value-of select="substring-after(.,'http://schema.omg.org/spec/UML/2.1/uml.xml')"/>
	   	</xsl:when> 
	
	   	<xsl:when test="contains(.,'http://schema.omg.org/spec/UML/2.1')">
		  	<xsl:text>pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml</xsl:text>
		  	<xsl:value-of select="substring-after(.,'http://schema.omg.org/spec/UML/2.1')"/>
	   	</xsl:when> 
	
	   	<xsl:when test="contains(.,'http://schema.omg.org/spec/UML/2.1.1/uml.xml')">
		 	<xsl:text>pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml</xsl:text>
		  	<xsl:value-of select="substring-after(.,'http://schema.omg.org/spec/UML/2.1.1/uml.xml')"/>
	   	</xsl:when>  
	
	   	<xsl:when test="contains(.,'http://schema.omg.org/spec/UML/2.2/uml.xml')">
		  	<xsl:text>pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml</xsl:text>
		  	<xsl:value-of select="substring-after(.,'http://schema.omg.org/spec/UML/2.2/uml.xml')"/>
	   	</xsl:when>
	
	   	<xsl:when test="contains(.,'http://schema.omg.org/spec/UML/2.3/uml.xml')">
		  	<xsl:text>pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml</xsl:text>
		  	<xsl:value-of select="substring-after(.,'http://schema.omg.org/spec/UML/2.3/uml.xml')"/>
	   	</xsl:when>  
	                                            
	   	<xsl:when test="contains(.,'http://www.omg.org/spec/UML/20100901/PrimitiveTypes.xmi')">
		  	<xsl:text>pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml</xsl:text>
		  	<xsl:value-of select="substring-after(.,'http://schema.omg.org/spec/UML/2.3/uml.xml')"/>
	   	</xsl:when>
	
	   	<xsl:otherwise> <xsl:value-of select="."/>  </xsl:otherwise>

  	</xsl:choose>
	
	</xsl:attribute> 
</xsl:template>


 <!-- Change realizing classifier attribut if the element is not a componentRealization -->
<xsl:template match="/*[@xmi:type!='uml:ComponentRealization']/@realizingClassifier"> 
	<xsl:attribute name="client" namespace=""><xsl:value-of select="."/></xsl:attribute> 
</xsl:template> 



<!-- Clean memberEnd elements -->
<xsl:template match="//*[./memberEnd[@xmi:idref]][count(./memberEnd[@xmi:idref]) = 1]">
	<xsl:copy> 
  		<xsl:variable name= "memberEnd">
    		<xsl:value-of select="concat(@memberEnd,' ', ./memberEnd/@xmi:idref)"/>
  		</xsl:variable>
  		<xsl:attribute name="memberEnd" namespace=""><xsl:value-of select="$memberEnd"/></xsl:attribute> 
  		<xsl:apply-templates select="@* | * | text()"/> 
  	</xsl:copy>
</xsl:template> 

<xsl:template match="//*[./memberEnd[@xmi:idref]][count(./memberEnd[@xmi:idref]) = 2]">
	<xsl:copy> 
  		<xsl:variable name= "memberEnd">
    		<xsl:value-of select="concat(./memberEnd[1]/@xmi:idref,' ', ./memberEnd[2]/@xmi:idref)"/>
 	 	</xsl:variable>
  		<xsl:attribute name="memberEnd" namespace=""><xsl:value-of select="$memberEnd"/></xsl:attribute> 
  		<xsl:apply-templates select="@* | * | text()"/> 
  	</xsl:copy>
</xsl:template> 

<!-- Clean references for incomming edge  -->
<xsl:template match="//*[./incoming[@xmi:idref]]">
	<xsl:copy> 
  		<xsl:variable name= "incoming">
  			<xsl:for-each select="./incoming/@xmi:idref">
    			<xsl:value-of select="concat(.,' ')"/>
  			</xsl:for-each>
  		</xsl:variable>
  		<xsl:attribute name="incoming" namespace=""><xsl:value-of select="$incoming"/></xsl:attribute> 
  		<xsl:apply-templates select="@* | * | text()"/> 
  	</xsl:copy>
</xsl:template> 

<!-- Clean references for outgoing edge  -->
<xsl:template match="//*[./outgoing[@xmi:idref]]">
	<xsl:copy> 
  		<xsl:variable name= "outgoing">
  			<xsl:for-each select="./outgoing/@xmi:idref">
    			<xsl:value-of select="concat(.,' ')"/>
  			</xsl:for-each>
  		</xsl:variable>
  		<xsl:attribute name="outgoing" namespace=""><xsl:value-of select="$outgoing"/></xsl:attribute> 
  		<xsl:apply-templates select="@* | * | text()"/> 
  	</xsl:copy>
</xsl:template> 

<!-- Clean references for generalization link -->
<xsl:template match="//generalization[./general[@href] ]">
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


<!-- Verify value of messageKind attribute -->        
<xsl:template match="//@messageKind"> 
	<xsl:if test=". != 'complete'">
		<xsl:attribute name="messageKind" namespace=""> 
			<xsl:value-of select="."/>
		</xsl:attribute>
	</xsl:if>
</xsl:template>


<!-- Clean Entreprise Architect profiles --> 
<xsl:template match="//xmi:Extension[@extender='Enterprise Architect']/profiles//*"> 
	<xsl:copy>
		<xsl:apply-templates select="@* | * | text()"/> 
	</xsl:copy>	
</xsl:template> 


<xsl:template match="//extend[@xmi:type='uml:Extend']"> 
	<xsl:copy>
		<xsl:apply-templates select="@* | * | text()"/> 
	</xsl:copy>
	
	<xsl:if test="./extensionLocation != Empty">
		<xsl:attribute name="extensionLocation" namespace=""> 
			<xsl:value-of select="./extensionLocation/@xmi:idref"/>
		</xsl:attribute>
	</xsl:if>	
</xsl:template> 
 
<xsl:template match="//extend[@xmi:type='uml:Extend']/extensionLocation"> 
	<xsl:apply-templates select="@* | * | text()"/>
</xsl:template>  


<xsl:template match="//extensionPoint[@xmi:type='uml:ExtensionPoint']/extension"> 
	<xsl:apply-templates select="@* | * | text()"/>
</xsl:template> 


<xsl:template match="//xmi:Extension[@extender='MagicDraw']"> 
	<xsl:apply-templates select="@* | * | text()"/>
</xsl:template>


<xsl:template match="//xmi:Extension[@extender='MagicDraw']//modelExtension"> 
	<xsl:copy>
		<xsl:apply-templates select="@* | * | text()"/> 
	</xsl:copy>
</xsl:template>


<xsl:template match="//xmi:Extension[@extender='MagicDraw']//modelExtension//*"> 
	<xsl:copy>
		<xsl:apply-templates select="@* | * | text()"/> 
	</xsl:copy>
</xsl:template>


<xsl:template match="//packagedElement[@xmi:type='uml:Component']//provided"/> 

<xsl:template match="//packagedElement[@xmi:type='uml:Component']//required"/> 

<xsl:template match="//xmi:Extension[@extender='Enterprise Architect']//text()"/> 

<xsl:template match="//xmi:Extension[@extender='Enterprise Architect']//profiles//*/text()"> 
	<xsl:copy-of select="."/>
</xsl:template>


<xsl:template match="//xmi:Extension[@extender='Enterprise Architect']"/> 

<xsl:template match="//xmi:Extension[@extender='Enterprise Architect']//*"/> 


<xsl:template match="//packagedElement[@xmi:type='uml:InformationFlow']/@source"> 
	<xsl:if test="not(@informationSource)">
		<xsl:attribute name="informationSource" namespace=""> 	
			<xsl:value-of select="."/>
		</xsl:attribute>
	</xsl:if>
</xsl:template>

<xsl:template match="//packagedElement[@xmi:type='uml:InformationFlow']/@target"> 
	<xsl:if test="not(@informationTarget)">
		<xsl:attribute name="informationTarget" namespace=""> 	
			<xsl:value-of select="."/>
		</xsl:attribute>
	</xsl:if>	
</xsl:template>

<xsl:template match="//*[./covered[@xmi:idref]]">
  <xsl:copy> 
  	<xsl:variable name="covered"><xsl:value-of select="./covered/@xmi:idref"/></xsl:variable>
  	<xsl:attribute name="covered" namespace="">
  	<xsl:for-each select="//*[@xmi:id=$covered]">
    	<xsl:if test="./@xmi:type = 'uml:Property'">
    		<xsl:value-of select="$covered"/>
    	</xsl:if>
  	</xsl:for-each> 
  	</xsl:attribute> 
  	<xsl:apply-templates select="@* | * | text()"/> 
  </xsl:copy>  
</xsl:template> 


<!-- Reproduce all nodes  -->
<xsl:template match=" * | @*  | text() ">	
	<xsl:copy>
		<xsl:apply-templates select="@* | * | text()"/>
	</xsl:copy>	
</xsl:template> 

</xsl:stylesheet>