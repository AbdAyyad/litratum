<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="article">
        <xsl:text>&#xa;</xsl:text>
        <xsl:call-template name="paragraph"/>
    </xsl:template>

    <xsl:template name="paragraph">
        <xsl:for-each select="body/p">
            <p>
                <xsl:value-of select="text()"/>
            </p>
            <xsl:text>&#xa;</xsl:text>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>