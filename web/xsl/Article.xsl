<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="article">
        <xsl:text>&#xa;</xsl:text>
        <xsl:apply-templates/>
    </xsl:template>
    <!-- meta in article database XSL -->
    <xsl:template match="front">

    </xsl:template>

    <xsl:template match="body">
        <xsl:apply-templates/>
    </xsl:template>


    <xsl:template match="graphic">
        <xsl:value-of select="@id"/>
        <xsl:text>&#xa;</xsl:text>
    </xsl:template>


    <xsl:template match="p">
        <p>
            <xsl:value-of select="text()"/>
        </p>
        <xsl:text>&#xa;</xsl:text>
    </xsl:template>

</xsl:stylesheet>