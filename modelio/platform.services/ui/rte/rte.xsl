<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Copyright (c) 2005, 2006 IBM Corporation and others.
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the Eclipse Public License v1.0
    which accompanies this distribution, and is available at
    http://www.eclipse.org/legal/epl-v10.html
    Contributors:
    IBM Corporation - initial implementation
-->

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

<xsl:template match="/rte">
	<xsl:variable name="rte_id"><xsl:value-of select="@id"/></xsl:variable>
	<xsl:variable name="rte_js"><xsl:value-of select="@js"/></xsl:variable>
	<html>
		<head>
			<script language="JavaScript" type="text/javascript" src="{$rte_js}"></script>
		</head>
		<body leftMargin="0" topMargin="0" marginheight="0" marginwidth="0" scroll="no" style="overflow: hidden">
			<iframe id="{$rte_id}" name="{$rte_id}" style="border: none;" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>
			<script language="JavaScript" type="text/javascript">
				initEditor('<xsl:value-of select="@id"/>', '<xsl:value-of select="@css"/>', '<xsl:value-of select="@baseURL"/>');
			</script>
		</body>
	</html>
</xsl:template>

</xsl:stylesheet>
