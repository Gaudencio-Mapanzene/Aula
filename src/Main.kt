import java.awt.BasicStroke
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.UIManager

class JanelaMultimedia : JFrame() {

    private val corFundo = Color(16, 16, 16)
    private val corBotao = Color(8, 8, 8)
    private val corHover = Color(81, 72, 222)
    private val corBranca = Color(245, 245, 245)

    init {
        title = "Multimédia"
        defaultCloseOperation = EXIT_ON_CLOSE
        isResizable = false

        // Define o tamanho da área interna da janela.
        contentPane.preferredSize = Dimension(274, 448)

        criarInterface()

        pack()
        setLocationRelativeTo(null)
    }

    private fun criarInterface() {
        val painelPrincipal = PainelComGrade()

        painelPrincipal.layout = null
        painelPrincipal.preferredSize = Dimension(274, 448)

        contentPane = painelPrincipal

        val painelConteudo = JPanel(null).apply {
            background = corFundo

            border = BorderFactory.createLineBorder(
                corBranca,
                1
            )

            setBounds(27, 15, 221, 400)
        }

        val botaoVideo = criarBotao(
            texto = "Vídeo",
            x = 49,
            y = 39
        )

        val botaoImagem = criarBotao(
            texto = "Imagem",
            x = 49,
            y = 160
        )

        val botaoAudio = criarBotao(
            texto = "Áudio",
            x = 49,
            y = 290
        )

        botaoVideo.addActionListener {
            JOptionPane.showMessageDialog(
                this,
                "Opção Vídeo selecionada.",
                "Vídeo",
                JOptionPane.INFORMATION_MESSAGE
            )
        }

        botaoImagem.addActionListener {
            JOptionPane.showMessageDialog(
                this,
                "Opção Imagem selecionada.",
                "Imagem",
                JOptionPane.INFORMATION_MESSAGE
            )
        }

        botaoAudio.addActionListener {
            JOptionPane.showMessageDialog(
                this,
                "Opção Áudio selecionada.",
                "Áudio",
                JOptionPane.INFORMATION_MESSAGE
            )
        }

        painelConteudo.add(botaoVideo)
        painelConteudo.add(botaoImagem)
        painelConteudo.add(botaoAudio)

        painelPrincipal.add(painelConteudo)
    }

    private fun criarBotao(
        texto: String,
        x: Int,
        y: Int
    ): JButton {
        return JButton(texto).apply {
            font = Font("Arial", Font.PLAIN, 18)
            foreground = corBranca
            background = corBotao

            isOpaque = true
            isFocusPainted = false
            isContentAreaFilled = true
            isBorderPainted = true

            border = BorderFactory.createLineBorder(
                corBranca,
                1
            )

            cursor = java.awt.Cursor.getPredefinedCursor(
                java.awt.Cursor.HAND_CURSOR
            )

            setBounds(x, y, 121, 41)

            addMouseListener(object : MouseAdapter() {
                override fun mouseEntered(event: MouseEvent) {
                    background = corHover
                }

                override fun mouseExited(event: MouseEvent) {
                    background = corBotao
                }
            })
        }
    }

    /**
     * Painel responsável pelo fundo escuro com uma grade discreta.
     */
    private inner class PainelComGrade : JPanel() {

        init {
            background = Color(10, 10, 10)
        }

        override fun paintComponent(graphics: Graphics) {
            super.paintComponent(graphics)

            val g2 = graphics.create() as Graphics2D

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            )

            g2.color = Color(31, 31, 31)
            g2.stroke = BasicStroke(1f)

            val tamanhoCelula = 10

            for (x in 0 until width step tamanhoCelula) {
                g2.drawLine(x, 0, x, height)
            }

            for (y in 0 until height step tamanhoCelula) {
                g2.drawLine(0, y, width, y)
            }

            g2.dispose()
        }
    }
}

fun main() {
    SwingUtilities.invokeLater {
        // Mantém a aparência consistente entre sistemas operativos.
        UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName()
        )

        JanelaMultimedia().isVisible = true
    }
}