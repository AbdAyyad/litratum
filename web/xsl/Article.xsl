<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <xsl:apply-templates mode="print" select="article"/>
    </xsl:template>

    <xsl:template match="front/article-meta">

    </xsl:template>
</xsl:stylesheet>