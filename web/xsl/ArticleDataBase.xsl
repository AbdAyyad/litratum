<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="article">
        <xsl:text>&#xa;</xsl:text>
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="front">
        <xsl:value-of select="article-meta/article-userId"/>
    </xsl:template>

    <xsl:template match="body">

    </xsl:template>


</xsl:stylesheet>