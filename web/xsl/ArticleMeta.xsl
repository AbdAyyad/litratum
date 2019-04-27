<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="article">
        <xsl:text>&#xa;</xsl:text>
        <xsl:call-template name="author"/>
        <xsl:text>&#xa;</xsl:text>
        <xsl:call-template name="article-doi"/>
    </xsl:template>

    <xsl:template name="author">
        <xsl:value-of select="front/article-meta/contrib-group/contrib/string-name/given-names"/>
        <xsl:text> </xsl:text>
        <xsl:value-of select="front/article-meta/contrib-group/contrib/string-name/surname"/>
    </xsl:template>

    <xsl:template name="article-doi">
        <xsl:value-of select="front/article-meta/article-id"/>
    </xsl:template>
</xsl:stylesheet>